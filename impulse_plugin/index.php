<?php

/*
Plugin Name: Impulse Plugin
Plugin URI: http://URI_Of_Page_Describing_Plugin_and_Updates
Description: Plugin dédié à l'ajout de fonctionnalités pour le site Impulse
Version: 1.0
Author: Ayoub ESSALHI
Author URI: http://URI_Of_The_Plugin_Author
License: A "Slug" license name e.g. GPL2
*/

function list_estate_html()
{
    $html = '
<div class="estates-list">
        <div class="horizontal-estate-card">
        <div class="estate-photo-container">
            <img src="https://geneva-3d.com/wp-content/uploads/2019/02/home.jpg" alt="Mountain View">
        </div>
        <div class="estate-info-container">
            <div class="card-content">
                <h3 class="card-text estate-title"><span>Intitulé du bien</span></h3>
                <p class="card-text  estate-description">00000 Ville, Pays</p>
                <p class="card-text estate-price">Prix (en €)</p>
                <p class="card-text estate-description">
                    Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean mauris neque, consectetur nec maximus at, venenatis a neque. Quisque gravida felis leo, vitae euismod ipsum mollis eu. Cras tincidunt id ligula ut vehicula. Proin semper nunc at lacus tincidunt, vitae fringilla odio pellentesque. Sed facilisis neque est. Mauris euismod interdum massa, ac aliquam elit pulvinar eu. Cras egestas mollis ornare. Donec ac dui ultricies, tincidunt erat sit amet, varius orci. Mauris dignissim, augue quis convallis vestibulum, elit neque malesuada turpis, quis lobortis libero nunc vel est. Quisque consectetur venenatis fringilla. Integer lacus elit, dictum in felis sed, mollis semper turpis. Quisque sed libero malesuada, sollicitudin erat sed, facilisis turpis. Sed imperdiet purus non odio dignissim convallis. Etiam eget convallis quam. Fusce pulvinar nibh nulla, in facilisis ex dignissim posuere. Curabitur nec elit id eros volutpat gravida eu molestie libero.
                </p>
            </div>
            <div class="bottom-bar">
                <button class="virtual-visit-button">Visite Virtuelle</button>
            </div>
        </div>
    </div>
        <div class="horizontal-estate-card">
        <div class="estate-photo-container">
            <img src="https://geneva-3d.com/wp-content/uploads/2019/02/home.jpg" alt="Mountain View">
        </div>
        <div class="estate-info-container">
            <div class="card-content">
                <h3 class="card-text estate-title"><span>Intitulé du bien</span></h3>
                <p class="card-text  estate-description">00000 Ville, Pays</p>
                <p class="card-text estate-price">Prix (en €)</p>
                <p class="card-text estate-description">
                    Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean mauris neque, consectetur nec maximus at, venenatis a neque. Quisque gravida felis leo, vitae euismod ipsum mollis eu. Cras tincidunt id ligula ut vehicula. Proin semper nunc at lacus tincidunt, vitae fringilla odio pellentesque. Sed facilisis neque est. Mauris euismod interdum massa, ac aliquam elit pulvinar eu. Cras egestas mollis ornare. Donec ac dui ultricies, tincidunt erat sit amet, varius orci. Mauris dignissim, augue quis convallis vestibulum, elit neque malesuada turpis, quis lobortis libero nunc vel est. Quisque consectetur venenatis fringilla. Integer lacus elit, dictum in felis sed, mollis semper turpis. Quisque sed libero malesuada, sollicitudin erat sed, facilisis turpis. Sed imperdiet purus non odio dignissim convallis. Etiam eget convallis quam. Fusce pulvinar nibh nulla, in facilisis ex dignissim posuere. Curabitur nec elit id eros volutpat gravida eu molestie libero.
                </p>
            </div>
            <div class="bottom-bar">
                <button class="virtual-visit-button">Visite Virtuelle</button>
            </div>
        </div>
    </div>
</div>';
    return $html;
}

function grid_estate_html()
{
    $html = '
        <div class="grid">
    <div class="vertical-estate-card">
    <div class="estate-photo-container">
        <img src="https://geneva-3d.com/wp-content/uploads/2019/02/home.jpg" alt="Mountain View">
    </div>
    <div class="estate-info-container">
        <div class="card-content">
            <h3 class="card-text estate-title"><span>Intitulé du bien</span></h3>
            <p class="card-text estate-description">00000 Ville, Pays</p>
            <p class="estate-price">Prix (en €)</p>
            <p class="card-text estate-description">
                Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean mauris neque, consectetur nec maximus at, venenatis a neque. Quisque gravida felis leo, vitae euismod ipsum mollis eu. Cras tincidunt id ligula ut vehicula. Proin semper nunc at lacus tincidunt, vitae fringilla odio pellentesque. Sed facilisis neque est. Mauris euismod interdum massa, ac aliquam elit pulvinar eu. Cras egestas mollis ornare. Donec ac dui ultricies, tincidunt erat sit amet, varius orci. Mauris dignissim, augue quis convallis vestibulum, elit neque malesuada turpis, quis lobortis libero nunc vel est. Quisque consectetur venenatis fringilla. Integer lacus elit, dictum in felis sed, mollis semper turpis. Quisque sed libero malesuada, sollicitudin erat sed, facilisis turpis. Sed imperdiet purus non odio dignissim convallis. Etiam eget convallis quam. Fusce pulvinar nibh nulla, in facilisis ex dignissim posuere. Curabitur nec elit id eros volutpat gravida eu molestie libero.
            </p>
        </div>
        <div class="bottom-bar">
            <button class="virtual-visit-button">Visite Virtuelle</button>
        </div>
    </div>
</div>
    <div class="vertical-estate-card">
        <div class="estate-photo-container">
            <img src="https://geneva-3d.com/wp-content/uploads/2019/02/home.jpg" alt="Mountain View">
        </div>
        <div class="estate-info-container">
            <div class="card-content">
                <h3 class="card-text estate-title"><span>Intitulé du bien</span></h3>
                <p class="card-text estate-description">00000 Ville, Pays</p>
                <p class="estate-price">Prix (en €)</p>
                <p class="card-text estate-description">
                    Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean mauris neque, consectetur nec maximus at, venenatis a neque. Quisque gravida felis leo, vitae euismod ipsum mollis eu. Cras tincidunt id ligula ut vehicula. Proin semper nunc at lacus tincidunt, vitae fringilla odio pellentesque. Sed facilisis neque est. Mauris euismod interdum massa, ac aliquam elit pulvinar eu. Cras egestas mollis ornare. Donec ac dui ultricies, tincidunt erat sit amet, varius orci. Mauris dignissim, augue quis convallis vestibulum, elit neque malesuada turpis, quis lobortis libero nunc vel est. Quisque consectetur venenatis fringilla. Integer lacus elit, dictum in felis sed, mollis semper turpis. Quisque sed libero malesuada, sollicitudin erat sed, facilisis turpis. Sed imperdiet purus non odio dignissim convallis. Etiam eget convallis quam. Fusce pulvinar nibh nulla, in facilisis ex dignissim posuere. Curabitur nec elit id eros volutpat gravida eu molestie libero.
                </p>
            </div>
            <div class="bottom-bar">
                <button class="virtual-visit-button">Visite Virtuelle</button>
            </div>
        </div>
    </div>
    <div class="vertical-estate-card">
        <div class="estate-photo-container">
            <img src="https://geneva-3d.com/wp-content/uploads/2019/02/home.jpg" alt="Mountain View">
        </div>
        <div class="estate-info-container">
            <div class="card-content">
                <h3 class="card-text estate-title"><span>Intitulé du bien</span></h3>
                <p class="card-text estate-description">00000 Ville, Pays</p>
                <p class="estate-price">Prix (en €)</p>
                <p class="card-text estate-description">
                    Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean mauris neque, consectetur nec maximus at, venenatis a neque. Quisque gravida felis leo, vitae euismod ipsum mollis eu. Cras tincidunt id ligula ut vehicula. Proin semper nunc at lacus tincidunt, vitae fringilla odio pellentesque. Sed facilisis neque est. Mauris euismod interdum massa, ac aliquam elit pulvinar eu. Cras egestas mollis ornare. Donec ac dui ultricies, tincidunt erat sit amet, varius orci. Mauris dignissim, augue quis convallis vestibulum, elit neque malesuada turpis, quis lobortis libero nunc vel est. Quisque consectetur venenatis fringilla. Integer lacus elit, dictum in felis sed, mollis semper turpis. Quisque sed libero malesuada, sollicitudin erat sed, facilisis turpis. Sed imperdiet purus non odio dignissim convallis. Etiam eget convallis quam. Fusce pulvinar nibh nulla, in facilisis ex dignissim posuere. Curabitur nec elit id eros volutpat gravida eu molestie libero.
                </p>
            </div>
            <div class="bottom-bar">
                <button class="virtual-visit-button">Visite Virtuelle</button>
            </div>
        </div>
    </div>
    <div class="vertical-estate-card">
        <div class="estate-photo-container">
            <img src="https://geneva-3d.com/wp-content/uploads/2019/02/home.jpg" alt="Mountain View">
        </div>
        <div class="estate-info-container">
            <div class="card-content">
                <h3 class="card-text estate-title"><span>Intitulé du bien</span></h3>
                <p class="card-text estate-description">00000 Ville, Pays</p>
                <p class="estate-price">Prix (en €)</p>
                <p class="card-text estate-description">
                    Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean mauris neque, consectetur nec maximus at, venenatis a neque. Quisque gravida felis leo, vitae euismod ipsum mollis eu. Cras tincidunt id ligula ut vehicula. Proin semper nunc at lacus tincidunt, vitae fringilla odio pellentesque. Sed facilisis neque est. Mauris euismod interdum massa, ac aliquam elit pulvinar eu. Cras egestas mollis ornare. Donec ac dui ultricies, tincidunt erat sit amet, varius orci. Mauris dignissim, augue quis convallis vestibulum, elit neque malesuada turpis, quis lobortis libero nunc vel est. Quisque consectetur venenatis fringilla. Integer lacus elit, dictum in felis sed, mollis semper turpis. Quisque sed libero malesuada, sollicitudin erat sed, facilisis turpis. Sed imperdiet purus non odio dignissim convallis. Etiam eget convallis quam. Fusce pulvinar nibh nulla, in facilisis ex dignissim posuere. Curabitur nec elit id eros volutpat gravida eu molestie libero.
                </p>
            </div>
            <div class="bottom-bar">
                <button class="virtual-visit-button">Visite Virtuelle</button>
            </div>
        </div>
    </div>
</div>
    ';
    return $html;
}

add_shortcode('list_estate', 'list_estate_html');
add_shortcode('grid_estate', 'grid_estate_html');

function add_css()
{
    wp_enqueue_style('impulse-css', plugin_dir_url(__FILE__) . 'css/impulse.css');
}

add_action('wp_footer', 'add_css');
