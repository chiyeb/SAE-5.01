<?php

/**
 * Fichier : GetEstateType.php
 *
 * Ce fichier contient la classe GetEstateType, responsable de la récupération de la liste
 * des types de biens immobiliers depuis une API externe.
 */

/**
 *
 * /!\ Informations /!\
 * Certains éléments de la documentation / des commentaires ont été générés par une intelligence artificiel,
 * les éléments principaux / importants ont été commentés par nos soins.
 */

require_once 'domain/estate_type.php';

use domain\EstateType;

/**
 * Classe GetEstateType
 *
 * Cette classe permet de récupérer tous les types de biens immobiliers via un appel à l’API
 * (à l’aide de WordPress HTTP API).
 */
class GetEstateType
{
    /**
     * @var string $request_url URL de base pour effectuer la requête de récupération des types de biens
     */
    private string $request_url;

    /**
     * Constructeur
     *
     * @param string|null $api_url (optionnel) URL de l’API pour récupérer les types de biens
     */
    public function __construct($api_url = null)
    {
        $this->request_url = $api_url;
    }

    /**
     * Récupère la liste de tous les types de biens immobiliers à partir d'une API externe
     *
     * @return array|\WP_Error Retourne un tableau d’objets EstateType ou WP_Error en cas d’erreur
     */
    public function get_all_estate_types()
    {
        // Effectue une requête HTTP GET avec un timeout de 10 secondes
        $response = wp_remote_get($this->request_url, array(
            'timeout' => 10,
        ));

        // Vérifie si la requête a échoué
        if (is_wp_error($response)) {
            return $response; // WP_Error avec le détail de l’erreur
        }

        // Vérifie le code HTTP de la réponse
        $status_code = wp_remote_retrieve_response_code($response);
        if (200 !== $status_code) {
            return new WP_Error(
                'api_error',
                'Réponse API invalide. Code HTTP: ' . $status_code
            );
        }

        // Récupère le corps (body) de la réponse
        $body = wp_remote_retrieve_body($response);
        if (empty($body)) {
            return new WP_Error(
                'api_empty',
                'La réponse de l’API est vide'
            );
        }

        // Décode les données JSON
        $data = json_decode($body, true);

        // Construction d’un tableau d’objets EstateType
        $estate_types = [];
        foreach ($data as $estate_type) {
            // Crée une instance d’EstateType pour chaque entrée
            $estate_type_obj = new EstateType($estate_type['code'], $estate_type['name']);
            $estate_types[] = $estate_type_obj;
        }

        // Retourne le tableau d’objets EstateType
        return $estate_types;
    }
}
