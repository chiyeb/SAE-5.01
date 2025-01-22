<?php
require_once 'get_estate.php';

/**
 * Enregistre une route REST pour récupérer plusieurs biens par leurs IDs
 */
add_action('rest_api_init', function () {
    register_rest_route('impulse/v1', '/get_estates_by_ids', [
        'methods'             => 'GET',
        'callback'            => 'impulse_rest_get_estates_by_ids',
        'permission_callback' => '__return_true', // à sécuriser au besoin
    ]);
});

/**
 * Callback de la route REST impulse/v1/get_estates_by_ids
 * Exemple d’appel : /wp-json/impulse/v1/get_estates_by_ids?ids=12,15,99
 */
function impulse_rest_get_estates_by_ids(\WP_REST_Request $request) {
    // ça recup les IDs passés en paramètre GET => ?ids=1,2,3
    $idsParam = $request->get_param('ids');
    if (empty($idsParam)) {
        return new \WP_REST_Response(['error' => 'Aucun ID fourni.'], 400);
    }

    // tranforme la liste CSV en array
    $ids = array_map('trim', explode(',', $idsParam));

    $getEstate = new \GetEstate('https://impulsepasserelle.alwaysdata.net/annonce/get');

    $response = $getEstate->get_estates_by_ids($ids);

    if (is_wp_error($response)) {
        return new \WP_REST_Response(['error' => $response->get_error_message()], 500);
    }

    return new \WP_REST_Response($response, 200);
}
