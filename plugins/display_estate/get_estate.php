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
    public function get_all_estate()
    {
        return $this->apiRequest();
    }

    /**
     * Récupère les biens d’un pays donné depuis l’API.
     *
     * @param  string $pays
     * @return array|WP_Error
     */
    public function get_estate_by_country($pays)
    {
        return $this->apiRequest('', ['pays' => $pays]);
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
}
