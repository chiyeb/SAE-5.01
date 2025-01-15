<?php
/**
 * Plugin Name: Plugin API Biens en Vente
 * Description: Plugin pour afficher une liste de biens en vente avec un carrousel d'images.
 * Version: 1.12
 * Author: Raoul
 */

// Sécurité : Empêche l'accès direct au fichier.
if ( ! defined( 'ABSPATH' ) ) {
    exit;
}

/**
 * Simule la récupération des données depuis une API.
 *
 * @return array Tableau de données factices.
 */
function biens_en_vente_recuperer_donnees() {
    return array(
        array(
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
            'corps' => 'Maison de campagne cosy près du lac de Bordeaux, dans une résidence calme et grande.'
        ),
        array(
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
            'corps' => 'Appartement T2 idéalement situé au centre-ville de Paris, proche de toutes commodités et des transports en commun.'
        ),
        array(
            'images' => array(
                'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRWgb9GT9TaH4xTJuZxchJbql0qSurGDVqZ4A&s',
            ),
            'titre' => 'Studio proche université',
            'prix' => '80 000 €',
            'ville' => 'Lyon',
            'pays' => 'France',
            'type_bien' => 'Studio',
            'corps_impression' => 'Studio fonctionnel proche des universités de Lyon',
            'corps' => 'Studio fonctionnel et équipé, situé à proximité des universités de Lyon, idéal pour les étudiants.'
        ),

    );
}

/**
 * Shortcode [liste_bien_filtre] pour afficher les biens.
 *
 * @param array $atts Attributs du shortcode.
 * @return string HTML du contenu à afficher.
 */
function biens_en_vente_shortcode_annonces( $atts ) {
    $atts = shortcode_atts(
        array(
            'pays' => 'France',
        ),
        $atts,
        'liste_bien_filtre_filtre'
    );

    $annonces = biens_en_vente_recuperer_donnees();
    $pays = $atts['pays'];

    // Filtrer les annonces par pays.
    $annonces = array_filter( $annonces, function( $annonce ) use ( $pays ) {
        return isset( $annonce['pays'] ) && strtolower( $annonce['pays'] ) === strtolower( $pays );
    });

    // Aucun bien trouvé.
    if ( empty( $annonces ) ) {
        return '<p>Aucun bien en vente en : ' . esc_html( $pays ) . '.</p>';
    }

    ob_start();
    ?>

    <form class="biens-filtre-form">
        <!-- Type de bien -->
        <label for="type_bien">Type de bien :</label>
        <select id="type_bien" name="type_bien">
            <option value="">Tous</option>
            <option value="Appartement">Appartement</option>
            <option value="Maison">Maison</option>
            <!-- Ajoutez d'autres types si nécessaire -->
        </select>

        <!-- Prix minimum et maximum -->
        <label for="prix_min">Prix min :</label>
        <input type="number" id="prix_min" name="prix_min" placeholder="Min (€)">
        <label for="prix_max">Prix max :</label>
        <input type="number" id="prix_max" name="prix_max" placeholder="Max (€)">

        <!-- Ville -->
        <label for="ville">Ville :</label>
        <input type="text" id="ville" name="ville" placeholder="Nom de la ville">

        <!-- Surface habitable -->
        <label for="habitable_surface_min">Surface habitable min (m²) :</label>
        <input type="number" id="habitable_surface_min" name="habitable_surface_min">
        <label for="habitable_surface_max">Surface habitable max (m²) :</label>
        <input type="number" id="habitable_surface_max" name="habitable_surface_max">

        <!-- Classe énergie et climat -->
        <label for="class_energie">Classe énergie :</label>
        <select id="class_energie" name="class_energie">
            <option value="">Toutes</option>
            <option value="A">A</option>
            <option value="B">B</option>
            <option value="C">C</option>
            <option value="D">D</option>
            <!-- Ajoutez d'autres classes si nécessaire -->
        </select>
        <label for="class_climat">Classe climat :</label>
        <select id="class_climat" name="class_climat">
            <option value="">Toutes</option>
            <option value="A">A</option>
            <option value="B">B</option>
            <option value="C">C</option>
            <option value="D">D</option>
        </select>

        <!-- Nombre de chambres et salles de bain -->
        <label for="nb_chambres">Nombre de chambres :</label>
        <input type="number" id="nb_chambres" name="nb_chambres" min="0">
        <label for="nb_salle_bain">Nombre de salles de bain :</label>
        <input type="number" id="nb_salle_bain" name="nb_salle_bain" min="0">

        <!-- Piscine -->
        <label for="piscine">
            <input type="checkbox" id="piscine" name="piscine"> Avec piscine
        </label>

        <!-- Vue -->
        <label for="vue">Vue :</label>
        <select id="vue" name="vue">
            <option value="">Toutes</option>
            <option value="ville">Ville</option>
            <option value="mer">Mer</option>
            <option value="montagne">Montagne</option>
        </select>

        <!-- Orientation -->
        <label for="orientation">Orientation :</label>
        <select id="orientation" name="orientation">
            <option value="">Toutes</option>
            <option value="nord">Nord</option>
            <option value="sud">Sud</option>
            <option value="est">Est</option>
            <option value="ouest">Ouest</option>
            <option value="sud-est">Sud-Est</option>
        </select>

        <button type="submit">Filtrer</button>
    </form>


    <div class="annonces-liste">
        <?php foreach ( $annonces as $annonce ) : ?>
            <div class="annonce-carte">

                <?php if ( ! empty( $annonce['images'] ) && is_array( $annonce['images'] ) ) : ?>
                    <div class="annonce-carousel swiper">
                        <div class="swiper-wrapper">
                            <?php foreach ( $annonce['images'] as $image ) : ?>
                                <div class="swiper-slide">
                                    <img src="<?php echo esc_url( $image ); ?>" alt="<?php echo esc_attr( $annonce['titre'] ); ?>">
                                </div>
                            <?php endforeach; ?>
                        </div>
                        <div class="swiper-button-next"></div>
                        <div class="swiper-button-prev"></div>
                        <div class="swiper-pagination"></div>
                    </div>
                <?php endif; ?>

                <div class="annonce-details">
                    <h3 class="annonce-titre"><?php echo esc_html( $annonce['titre'] ); ?></h3>
                    <p class="annonce-description">
                        <?php echo esc_html( $annonce['corps_impression'] ?? substr( $annonce['corps'], 0, 80 ) . '...' ); ?>
                    </p>
                    <p class="annonce-prix"><?php echo esc_html( $annonce['prix'] ); ?></p>
                    <p class="annonce-localisation"><?php echo esc_html( $annonce['ville'] . ', ' . $annonce['pays'] ); ?></p>
                    <p class="annonce-type">Type de logement : <?php echo esc_html( $annonce['type_bien'] ); ?></p>
                </div>
            </div>
        <?php endforeach; ?>
    </div>
    <?php
    return ob_get_clean();
}

add_shortcode( 'liste_bien_filtre', 'biens_en_vente_shortcode_annonces' );

/**
 * Enqueue scripts et styles.
 */
function biens_en_vente_enqueue_scripts() {
    wp_enqueue_style( 'swiper-style', 'https://unpkg.com/swiper/swiper-bundle.min.css', array(), '8.4.5' );
    wp_enqueue_script( 'swiper-script', 'https://unpkg.com/swiper/swiper-bundle.min.js', array(), '8.4.5', true );
    wp_enqueue_style(
        'biens-en-vente-style',
        plugin_dir_url( __FILE__ ) . 'style.css',
        array(),
        '1.0.0'
    );
    wp_add_inline_script( 'swiper-script', "
        document.addEventListener('DOMContentLoaded', function () {
            const carousels = document.querySelectorAll('.annonce-carousel');
            carousels.forEach(carousel => {
                new Swiper(carousel, {
                    loop: true,
                    navigation: {
                        nextEl: '.swiper-button-next',
                        prevEl: '.swiper-button-prev',
                    },
                    pagination: {
                        el: '.swiper-pagination',
                        clickable: true,
                    },
                });
            });
        });
    ");
}
add_action( 'wp_enqueue_scripts', 'biens_en_vente_enqueue_scripts' );
function biens_pagination_shortcode($atts) {
    $atts = shortcode_atts(array(
        'pays' => 'France', // Pays par défaut
    ), $atts, 'biens_pagination');

    $annonces = biens_en_vente_recuperer_donnees();
    $pays = $atts['pays'];

    // Filtrer les annonces par pays
    $annonces = array_filter($annonces, function($annonce) use ($pays) {
        return isset($annonce['pays']) && strtolower($annonce['pays']) === strtolower($pays);
    });

    // Variables pour la pagination
    $items_per_page = 10; // Afficher 2 biens par page
    $total_pages = ceil(count($annonces) / $items_per_page);
    // Aucun bien trouvé.
    if ( empty( $annonces ) ) {
        return '<p>Aucun bien en vente en : ' . esc_html( $pays ) . '.</p>';
    }

    ob_start();
    ?>
    <div class="paginated-liste-biens">
        <?php for ($i = 0; $i < $total_pages; $i++) : ?>
            <div class="page-biens" id="page-<?php echo $i + 1; ?>" style="display: <?php echo $i === 0 ? 'block' : 'none'; ?>;">
                <div class="annonces-liste">
                    <?php
                    // Affiche les biens pour la page actuelle
                    $offset = $i * $items_per_page;
                    $biens = array_slice($annonces, $offset, $items_per_page);

                    foreach ($biens as $annonce) :
                        ?>
                        <div class="annonce-carte">
                            <?php if (!empty($annonce['images']) && is_array($annonce['images'])) : ?>
                                <div class="annonce-carousel swiper">
                                    <div class="swiper-wrapper">
                                        <?php foreach ($annonce['images'] as $image) : ?>
                                            <div class="swiper-slide">
                                                <img src="<?php echo esc_url($image); ?>" alt="<?php echo esc_attr($annonce['titre']); ?>">
                                            </div>
                                        <?php endforeach; ?>
                                    </div>
                                    <div class="swiper-button-next"></div>
                                    <div class="swiper-button-prev"></div>
                                    <div class="swiper-pagination"></div>
                                </div>
                            <?php endif; ?>

                            <div class="annonce-details">
                                <h3 class="annonce-titre"><?php echo esc_html($annonce['titre']); ?></h3>
                                <p class="annonce-description">
                                    <?php echo esc_html($annonce['corps_impression'] ?? substr($annonce['corps'], 0, 80) . '...'); ?>
                                </p>
                                <p class="annonce-prix"><?php echo esc_html($annonce['prix']); ?></p>
                                <p class="annonce-localisation"><?php echo esc_html($annonce['ville'] . ', ' . $annonce['pays']); ?></p>
                                <p class="annonce-type">Type de logement : <?php echo esc_html($annonce['type_bien']); ?></p>
                            </div>
                        </div>
                    <?php endforeach; ?>
                </div>
            </div>
        <?php endfor; ?>
    </div>

    <!-- Pagination -->
    <div class="pagination-container">
        <button class="pagination-prev" disabled>&laquo; Précédent</button>
        <div class="pagination-numbers">
            <?php for ($i = 0; $i < $total_pages; $i++) : ?>
                <button class="pagination-number" data-page="<?php echo $i + 1; ?>">
                    <?php echo $i + 1; ?>
                </button>
            <?php endfor; ?>
        </div>
        <button class="pagination-next">&raquo; Suivant</button>
    </div>
    <?php
    return ob_get_clean();
}

add_shortcode('biens_pagination', 'biens_pagination_shortcode');


function enqueue_pagination_scripts() {
    wp_enqueue_script('pagination-script', plugin_dir_url(__FILE__) . 'Pagination.js', array(), '1.0', true);

}
add_action('wp_enqueue_scripts', 'enqueue_pagination_scripts');


