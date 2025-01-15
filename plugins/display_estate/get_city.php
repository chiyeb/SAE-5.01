<?php

/**
 * Fichier : GetCity.php
 *
 * Cette classe se charge de récupérer la liste des villes via une API externe.
 */


/**
 *
 * /!\ Informations /!\
 * Certains éléments de la documentation / des commentaires ont été générés par une intelligence artificiel,
 * les éléments principaux / importants ont été commentés par nos soins.
 */

require_once 'domain/city.php';

use domain\City;

/**
 * Classe GetCity
 *
 * Gère la récupération des informations de villes (code postal, nom) depuis une API.
 */
class GetCity
{
    /**
     * @var string $request_url URL de base pour la requête de récupération des villes
     */
    private string $request_url;

    /**
     * Constructeur
     *
     * @param string|null $api_url (optionnel) URL de l’API permettant de récupérer les villes
     */
    public function __construct($api_url = null)
    {
        $this->request_url = $api_url;
    }

    /**
     * Récupère toutes les villes depuis l'API
     *
     * @return array|\WP_Error Retourne un tableau d’objets City ou WP_Error en cas d’erreur
     */
    public function get_all_cities()
    {
        // Construit l'URL finale avec les paramètres GET (ex: ?pays=FR)
        $final_url = $this->request_url . $this->get_request_params($_GET);

        // Effectue la requête HTTP GET vers l’API
        $response = wp_remote_get($final_url, array(
            'timeout' => 10,
        ));

        // Vérifie les éventuelles erreurs de requête
        if (is_wp_error($response)) {
            return $response; // WP_Error détaillant l’erreur
        }

        // Vérifie le code HTTP de la réponse
        $status_code = wp_remote_retrieve_response_code($response);
        if (200 !== $status_code) {
            return new \WP_Error(
                'api_error',
                'Réponse API invalide. Code HTTP: ' . $status_code
            );
        }

        // Récupère le corps (body) de la réponse
        $body = wp_remote_retrieve_body($response);
        if (empty($body)) {
            return new \WP_Error(
                'api_empty',
                'La réponse de l’API est vide.'
            );
        }

        // Décode la réponse JSON pour en faire un tableau associatif
        $data = json_decode($body, true);

        // Construit un tableau d’objets City
        $cities = [];
        foreach ($data as $city) {
            // Instancie un objet City pour chaque élément
            $city_obj = new City($city['cp'], $city['nom']);
            $cities[] = $city_obj;
        }

        // Retourne le tableau d’objets City
        return $cities;
    }

    /**
     * Construit la chaîne de paramètres GET pour l’URL (ex: ?pays=FR)
     *
     * @param array $params Tableau de paramètres passés (via $_GET)
     * @return string Chaîne de requête (ex: "?pays=FR")
     */
    private function get_request_params($params): string
    {
        // Initialise la chaîne de requête avec le point d’interrogation
        $paramStr = '?';

        // Ajoute le paramètre pays si présent et non vide
        if (isset($params['pays']) && !empty($params['pays'])) {
            $paramStr .= 'pays=' . urlencode($params['pays']);
        }

        return $paramStr;
    }
}
