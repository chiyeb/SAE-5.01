<?php

require_once 'domain/estate_type.php';

use domain\EstateType;

class GetEstateType
{
    private string $request_url;

    public function __construct($api_url = null)
    {
        $this->request_url = $api_url;
    }

    public function get_all_estate_types()
    {
        $response = wp_remote_get($this->request_url, array(
            'timeout' => 10,
        ));

        if (is_wp_error($response)) {
            return $response;
        }

        $status_code = wp_remote_retrieve_response_code($response);
        if (200 !== $status_code) {
            return new WP_Error(
                'api_error',
                'Réponse API invalide. Code HTTP: ' . $status_code
            );
        }

        $body = wp_remote_retrieve_body($response);
        if (empty($body)) {
            return new WP_Error(
                'api_empty',
                'La réponse de l’API est vide'
            );
        }

        $data = json_decode($body, true);

        $estate_types = [];
        foreach ($data as $estate_type) {
            $estate_type = new EstateType($estate_type['code'], $estate_type['name']);
            $estate_types[] = $estate_type;
        }
        return $estate_types;
    }
}