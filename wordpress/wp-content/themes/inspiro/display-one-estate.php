<?php

/**
 * Template Name: Afficher Bien
 * Description: Un template pour afficher un bien spécifique.
 */
get_header();

$ref = isset($_GET['id']) ? intval($_GET['id']) : 0;

if ($ref) {
    echo do_shortcode('[estate_page id="' . esc_attr($ref) . '"]');
} else {
    echo "Aucun bien sélectionner";
}

get_footer();
?>