<?php

/**
 *
 * /!\ Informations /!\
 * Certains éléments de la documentation / des commentaires ont été générés par une intelligence artificiel,
 * les éléments principaux / importants ont été commentés par nos soins.
 */

/**
 * Classe GetEstate
 *
 * Cette classe gère la récupération des données relatives aux biens immobiliers
 * depuis une API externe. Elle propose plusieurs méthodes pour obtenir la liste
 * complète des biens, filtrer par pays, récupérer un bien via son ID, etc.
 */
class GetEstate
{
    /**
     * @var string|null $api_url URL de base de l'API
     */
    private $api_url;

    /**
     * Constructeur
     *
     * @param string|null $url (optionnel) URL de base de l'API
     */
    public function __construct($url = null)
    {
        $this->api_url = $url;
    }

    /**
     * Récupère la liste de tous les biens depuis l’API (sans filtrage spécifique)
     *
     * @param array $params (optionnel) Paramètres de filtrage ou d’affichage
     * @return array|\WP_Error Renvoie un tableau associatif des données ou un WP_Error en cas d'erreur
     */
    public function get_all_estate($params = [])
    {
        // Prépare les filtres avant l’appel
        $filters = $this->get_filters($params);
        // Appel API générique (path vide) + filtres
        return $this->apiRequest('', $filters);
    }

    /**
     * Récupère les biens d’un pays donné depuis l’API
     *
     * @param string $pays  Code du pays ou identifiant (ex: 'FR')
     * @param array  $params (optionnel) Paramètres supplémentaires de filtrage ou d’affichage
     * @return array|\WP_Error Retourne un tableau associatif contenant les biens, ou WP_Error en cas d’erreur
     */
    public function get_estate_by_country($pays, $params = [])
    {
        // Récupère les filtres, puis force le paramètre 'pays'
        $filters = $this->get_filters($params);
        $filters['pays'] = $pays;
        return $this->apiRequest('', $filters);
    }

    /**
     * Récupère un bien par son ID depuis l’API
     *
     * @param int|string $id Identifiant unique du bien
     * @return array|\WP_Error Tableau associatif contenant les infos du bien, ou WP_Error en cas d’erreur
     */
    public function get_estate_by_id($id)
    {
        // Pas de filtres ici, l’ID est directement passé dans l’URL
        return $this->apiRequest($id);
    }

    /**
     * Récupère plusieurs biens par leurs identifiants depuis l’API
     *
     * @param array $ids Tableau contenant les identifiants uniques des biens
     * @return array | WP_Error Tableau associatif contenant deux clés :
     *               - 'success' : les biens récupérés sous forme de tableau associatif
     *               - 'errors' : les erreurs associées aux identifiants qui n'ont pas pu être récupérés
     */
    public function get_estates_by_ids($ids)
    {
        if (empty($ids) || !is_array($ids)) {
            return new \WP_Error(
                'invalid_ids',
                'Le paramètre des identifiants doit être un tableau non vide.'
            );
        }

        $results = [];
        $errors = [];

        foreach ($ids as $id) {
            $response = $this->get_estate_by_id($id);

            if (is_wp_error($response)) {
                $errors[$id] = $response->get_error_message();
            } else {
                $results[$id] = $response;
            }
        }
        return [
            'success' => $results,
            'errors' => $errors,
        ];
    }


    /**
     * Effectue l'appel à l'API et gère les différents cas d'erreurs
     *
     * @param string $path  Chemin supplémentaire à ajouter à l’URL de base (pour l’ID)
     * @param array  $query Paramètres GET à ajouter à l’URL
     *
     * @return array|\WP_Error Un tableau de données décodées (JSON) ou WP_Error en cas de problème
     */
    private function apiRequest($path = '', $query = [])
    {
        // Vérifie si l’URL de l’API est définie
        if (!$this->api_url) {
            return new \WP_Error(
                'api_error',
                'Aucune URL d’API n’est configurée.'
            );
        }

        // Construit l’URL finale
        $url = rtrim($this->api_url, '/');
        if (!empty($path)) {
            $url .= '/' . ltrim($path, '/');
        }

        // Ajoute les paramètres GET sous forme de query string
        if (!empty($query)) {
            $url = add_query_arg($query, $url);
        }

        // Effectue la requête distante
        $response = wp_remote_get($url, ['timeout' => 10]);

        // Vérifie si c’est une erreur de type WP_Error
        if (is_wp_error($response)) {
            return $response;
        }

        // Vérifie le code HTTP de la réponse
        $status_code = wp_remote_retrieve_response_code($response);
        if (200 !== $status_code) {
            return new \WP_Error(
                'api_error',
                'Réponse API invalide. Code HTTP: ' . $status_code
            );
        }

        // Récupère le contenu (body) de la réponse
        $body = wp_remote_retrieve_body($response);
        if (empty($body)) {
            return new \WP_Error(
                'api_empty',
                'La réponse de l’API est vide.'
            );
        }

        // Décode la réponse JSON
        $data = json_decode($body, true);
        if (json_last_error() !== JSON_ERROR_NONE) {
            return new \WP_Error(
                'api_json_error',
                'JSON invalide : ' . json_last_error_msg()
            );
        }

        // Retourne les données décodées
        return $data;
    }

    /**
     * Construit un tableau de filtres adaptés à l’API en se basant sur les paramètres reçus
     *
     * @param array $params Paramètres récupérés (GET ou autre) pour filtrer la requête API
     * @return array Tableau associatif de filtres
     */
    private function get_filters($params): array
    {
        $filters = [];

        // Filtre par ville
        if (!empty($params['ville'])) {
            $filters['ville'] = $params['ville'];
        }

        // Filtre par type de bien
        if (!empty($params['type_bien'])) {
            $filters['type_bien'] = $params['type_bien'];
        }

        // Filtre par budget maximum
        if (!empty($params['max_budget'])) {
            $filters['max_budget'] = $params['max_budget'];
        }

        // Filtre par surface
        if (!empty($params['surface'])) {
            $filters['surface'] = $params['surface'];
        }

        // Filtre par nombre de pièces
        if (!empty($params['nombre_pieces'])) {
            $filters['nombre_pieces'] = $params['nombre_pieces'];
        }

        // Filtre par nombre de chambres
        if (!empty($params['nombres_chambres'])) {
            $filters['nombres_chambres'] = $params['nombres_chambres'];
        }

        // Tri (ex: tri=prix&1 => tri sur prix en ordre croissant)
        if (!empty($params['tri'])) {
            $tris = explode('&', $params['tri']);
            $filters['tri_valeur'] = $tris[0];
            $filters['tri_croissant'] = $tris[1];
        }

        // Surface habitable min/max
        if (!empty($params['habitable_surface_min'])) {
            $filters['habitable_surface_min'] = $params['habitable_surface_min'];
        }
        if (!empty($params['habitable_surface_max'])) {
            $filters['habitable_surface_max'] = $params['habitable_surface_max'];
        }

        // Classe énergie (A, B, C, D, etc.)
        if (!empty($params['class_energie'])) {
            $filters['class_energie'] = $params['class_energie'];
        }

        // Classe climat (A, B, C, D, etc.)
        if (!empty($params['class_climat'])) {
            $filters['class_climat'] = $params['class_climat'];
        }

        // Nombre de salles de bain
        if (!empty($params['nb_salle_bain'])) {
            $filters['nb_salle_bain'] = $params['nb_salle_bain'];
        }

        // Piscine (0 ou 1)
        if (!empty($params['piscine'])) {
            $filters['piscine'] = $params['piscine'];
        }

        // Orientation (NORD, SUD, EST, OUEST)
        if (!empty($params['orientation'])) {
            $filters['orientation'] = $params['orientation'];
        }

        // Type d'offre (Vente, Location, etc.)
        if (!empty($params['type_offre'])) {
            $filters['type_offre'] = $params['type_offre'];
        }

        // Annonce meublé ou non
        if (!empty($params['meuble'])) {
            $filters['meuble'] = $params['meuble'];
        }

        return $filters;
    }
}
