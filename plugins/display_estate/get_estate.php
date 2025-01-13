<?php

class GetEstate
{
    private $api_url;

    function __construct($url=null)
    {
        $this->api_url = $url;
    }

    private $fake = array(
        array(
            'ref' => 100,
            'images' => array(
                'https://www.maisons-balency.fr/wp-content/uploads/2021/10/202112-construction-maisons-balency5-1400x633-c-center.jpg',
                'https://prod-saint-gobain-fr.content.saint-gobain.io/sites/saint-gobain.fr/files/2022-04/maison-contemporaine-la-maison-saint-gobain01.jpg',
                'https://manager.groupe-bdl.com/web-content/img/modeles/2021/11/131-modele-et-plan-maison-plain-pied-91d1df2-600x410.jpeg',
            ),
            'titre' => 'Maison de campagne',
            'prix' => '200 000 €',
            'ville' => 'Bordeaux',
            'pays' => 'France',
            'type_bien' => 'Maison',
            'corps_impression' => null,
            'corps' => 'Maison de campagne cosy près du lac de Bordeaux, dans une résidence calme et grande.',
            'habitable_surface' => 180,
            'terrain_surface' => 220,
            'class_energie' => "C",
            'class_climat' => "F",
            'nb_chambres' => 4,
            'nb_salle_bain' => 3,
            'piscine' => "non",
            'vue' => 'campagne',
            'orientation' => 'nord',
            'latitude' => 44.8378,
            'longitude' => -0.5795,
        ),
        array(
            'ref' => 120,
            'images' => array(
                'https://www.appartement-a-vendre.com/wp-content/uploads/2021/05/Appartement-Loue-a-Vendre-.jpeg',
                'https://monagentimmo.net/images/osproperty/category/thumbnail/1691628824_Appartementmonagentimmo.net.jpg',
            ),
            'titre' => 'Appartement T2 centre-ville',
            'prix' => '120 000 €',
            'ville' => 'Paris',
            'pays' => 'France',
            'type_bien' => 'Appartement',
            'corps_impression' => 'Appartement T2 idéalement situé au centre-ville de Paris',
            'corps' => 'Appartement T2 idéalement situé au centre-ville de Paris, proche de toutes commodités et des transports en commun.',
            'habitable_surface' => 50,
            'terrain_surface' => null,
            'class_energie' => "B",
            'class_climat' => "C",
            'nb_chambres' => 1,
            'nb_salle_bain' => 1,
            'piscine' => "non",
            'vue' => 'ville',
            'orientation' => 'sud-est',
            'latitude' => 48.8566,
            'longitude' => 2.3522,
        ),
        array(
            'ref' => 130,
            'images' => array(
                'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRWgb9GT9TaH4xTJuZxchJbql0qSurGDVqZ4A&s',
            ),
            'titre' => 'Studio proche université',
            'prix' => '80 000 €',
            'ville' => 'Lyon',
            'pays' => 'France',
            'type_bien' => 'Studio',
            'corps_impression' => 'Studio fonctionnel proche des universités de Lyon',
            'corps' => 'Studio fonctionnel et équipé, situé à proximité des universités de Lyon, idéal pour les étudiants.',
            'habitable_surface' => 25,
            'terrain_surface' => null,
            'class_energie' => "A",
            'class_climat' => "A",
            'nb_chambres' => 1,
            'nb_salle_bain' => 1,
            'piscine' => "non",
            'vue' => 'urbain',
            'orientation' => 'ouest',
            'latitude' => 45.7640,
            'longitude' => 4.8357,
        ),
    );


    /**
     * Récupère (ou simule) les données depuis l'API.
     *
     * @return array Tableau de données factices
     */

    public function fetch_fake_data()
    {
        return $this->fake;
    }

    public function fetch_fake_data_one_estate($ref)
    {
        foreach ($this->fake as $fakeEstate) {
            if ($fakeEstate['ref'] == $ref) {
                return $fakeEstate;
            }
        }
        return null;
    }

    public function get_all_estate()
    {
        $response = wp_remote_get($this->api_url, array(
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

        if (json_last_error() !== JSON_ERROR_NONE) {
            return new WP_Error(
                'api_json_error',
                'JSON invalide : ' . json_last_error_msg()
            );
        }
        return $data;
    }
    /**
     * Récupère les biens par pays depuis l'API ou les données factices.
     *
     * @param string $pays Le pays pour lequel récupérer les biens.
     * @return array|WP_Error Tableau des biens ou une erreur WP_Error.
     */
    public function get_estate_by_country($pays)
    {
        if (!$this->api_url) {
            return new WP_Error(
                'api_error',
                'Pas d URL d api initialisé'
            );
        }

        $response = wp_remote_get($this->api_url . '?pays=' . urlencode($pays), array(
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

        if (json_last_error() !== JSON_ERROR_NONE) {
            return new WP_Error(
                'api_json_error',
                'JSON invalide : ' . json_last_error_msg()
            );
        }
        return $data;
    }
    public function get_estate_by_id($id)
    {
        if (!$this->api_url) {
            return new WP_Error(
                'api_error',
                'Pas d URL d api initialisée'
            );
        }

        $endpoint = rtrim($this->api_url, '/') . '/' . urlencode($id);

        $response = wp_remote_get($endpoint, array(
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

        if (json_last_error() !== JSON_ERROR_NONE) {
            return new WP_Error(
                'api_json_error',
                'JSON invalide : ' . json_last_error_msg()
            );
        }

        return $data;
    }

}