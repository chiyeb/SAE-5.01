<?php

require_once 'domain/city.php';

use domain\City;

class GetCity
{
    private string $request_url;

    public function __construct($api_url = null)
    {
        $this->request_url = $api_url;
    }

    public function get_all_cities()
    {
        $response = wp_remote_get($this->request_url . $this->get_request_params($_GET), array(
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

        $cities = [];
        foreach ($data as $city) {
            $city = new City($city['cp'], $city['nom']);
            $cities[] = $city;
        }
        return $cities;
    }

    private function get_request_params($params): string
    {
        $paramStr = '?';
        if (isset($params['pays']) && !empty($params['pays'])) {
            $paramStr .= 'pays=' . urlencode($params['pays']);
        }
        return $paramStr;
    }
}