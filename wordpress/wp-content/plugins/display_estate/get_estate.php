<?php

class GetEstate
{
    private $api_url;

    public function __construct($url = null)
    {
        $this->api_url = $url;
    }

    /**
     * Récupère la liste de tous les biens depuis l’API.
     *
     * @return array|WP_Error
     */
    public function get_all_estate($params = [])
    {
        $filters = $this->get_filters($params);
        return $this->apiRequest('', $filters);
    }

    /**
     * Récupère les biens d’un pays donné depuis l’API.
     *
     * @param string $pays
     * @param array $params
     * @return array|WP_Error
     */
    public function get_estate_by_country($pays, $params = [])
    {
        $filters = $this->get_filters($params);
        $filters['pays'] = $pays;
        return $this->apiRequest('', $filters);
    }

    /**
     * Récupère un bien par son ID depuis l’API.
     *
     * @param  int|string $id
     * @return array|WP_Error
     */
    public function get_estate_by_id($id)
    {
        return $this->apiRequest($id);
    }

    /**
     * Méthode privée pour factoriser l’appel à l’API et la gestion des erreurs.
     *
     * @param  string $path    Éventuel segment d’URL.
     * @param  array  $query   Tableau associatif de paramètres GET.
     * @return array|WP_Error  Retourne un tableau de données décodées ou WP_Error en cas de souci.
     */
    private function apiRequest($path = '', $query = [])
    {
        if (!$this->api_url) {
            return new WP_Error(
                'api_error',
                'Aucune URL d’API n’est configurée.'
            );
        }

        $url = rtrim($this->api_url, '/');
        if (!empty($path)) {
            $url .= '/' . ltrim($path, '/');
        }

        if (!empty($query)) {
            $url = add_query_arg($query, $url);
        }

        $response = wp_remote_get($url, ['timeout' => 10]);

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
        if (json_last_error() !== JSON_ERROR_NONE) {
            return new WP_Error(
                'api_json_error',
                'JSON invalide : ' . json_last_error_msg()
            );
        }

        return $data;
    }

    private function get_filters($params): array
    {
        $filters = [];
        if (!empty($params['ville'])) {
            $filters['ville'] = $params['ville'];
        }
        if (!empty($params['type_bien'])) {
            $filters['type_bien'] = $params['type_bien'];
        }
        if (!empty($params['max_budget'])) {
            $filters['max_budget'] = $params['max_budget'];
        }
        if (!empty($params['surface'])) {
            $filters['surface'] = $params['surface'];
        }
        if (!empty($params['nombre_pieces'])) {
            $filters['nombre_pieces'] = $params['nombre_pieces'];
        }
        if (!empty($params['nombres_chambres'])) {
            $filters['nombres_chambres'] = $params['nombres_chambres'];
        }
        if (!empty($params['tri'])) {
            $tris = explode('&', $params['tri']);
            $filters['tri_valeur'] = $tris[0];
            $filters['tri_croissant'] = $tris[1];
        }
        if (!empty($params['habitable_surface_min'])) {
            $filters['habitable_surface_min'] = $params['habitable_surface_min'];
        }
        if (!empty($params['habitable_surface_max'])) {
            $filters['habitable_surface_max'] = $params['habitable_surface_max'];
        }
        if (!empty($params['class_energie'])) {
            $filters['class_energie'] = $params['class_energie'];
        }
        if (!empty($params['class_climat'])) {
            $filters['class_climat'] = $params['class_climat'];
        }
        if (!empty($params['nb_salle_bain'])) {
            $filters['nb_salle_bain'] = $params['nb_salle_bain'];
        }
        if (!empty($params['piscine'])) {
            $filters['piscine'] = $params['piscine'];
        }
        if (!empty($params['orientation'])) {
            $filters['orientation'] = $params['orientation'];
        }
        if(!empty($params['type_offre'])) {
            $filters['type_offre'] = $params['type_offre'];
        }
        return $filters;
    }
}