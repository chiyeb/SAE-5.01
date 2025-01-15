/**
 * Gestionnaire d'événement principal lancé après le chargement du DOM
 */

/**
 *
 * /!\ Informations /!\
 * Certains éléments de la documentation / des commentaires ont été générés par une intelligence artificiel,
 * les éléments principaux / importants ont été commentés par nos soins.
 */

document.addEventListener('DOMContentLoaded', function () {
    // Sélectionne le bouton "Voir plus" et la section des filtres supplémentaires
    const voirPlusButton = document.getElementById('voir-plus');
    const filtresSupplementaires = document.getElementById('filtres-supplementaires');

    /**
     * Gestion de l'affichage des filtres supplémentaires
     * --------------------------------------------------
     * Lorsque l'utilisateur clique sur "Voir plus", on inverse la classe "hidden",
     * et on ajoute ou retire la classe "show". On met à jour le texte du bouton en
     * fonction de l'état d'affichage.
     */
    if (voirPlusButton && filtresSupplementaires) {
        voirPlusButton.addEventListener('click', () => {
            // bascule la visibilité : si hidden existe, on l'enlève, sinon on l'ajoute
            const isVisible = filtresSupplementaires.classList.toggle('hidden');
            filtresSupplementaires.classList.toggle('show', !isVisible);

            // Met à jour le texte du bouton
            voirPlusButton.textContent = isVisible ? 'Voir plus' : 'Voir moins';
        });
    }

    /**
     * Gestion de la pagination
     * --------------------------------------------------
     * Récupère les différents éléments de la pagination (boutons de pages, boutons prev/next)
     * et gère l'affichage des différentes pages de biens.
     */
    const paginationNumbers = document.querySelectorAll('.pagination-number');
    const prevButton = document.querySelector('.pagination-prev');
    const nextButton = document.querySelector('.pagination-next');
    const pages = document.querySelectorAll('.property-page');

    // Page actuellement affichée
    let currentPage = 1;

    /**
     * Affiche la page donnée en paramètre (page) et masque les autres
     *
     * @param {number} page - Numéro de la page à afficher
     */
    function showPage(page) {
        pages.forEach((p, index) => {
            // index + 1 car index commence à 0, alors que page commence à 1
            p.style.display = (index + 1 === page) ? 'block' : 'none';
        });
        currentPage = page;
        updatePaginationButtons();
    }

    /**
     * Met à jour l'état (enabled/disabled) des boutons "Précédent" et "Suivant",
     * et ajoute la classe 'active' au bouton de la page en cours.
     */
    function updatePaginationButtons() {
        // Désactive le bouton "Précédent" si on est sur la première page
        prevButton.disabled = (currentPage === 1);
        // Désactive le bouton "Suivant" si on est sur la dernière page
        nextButton.disabled = (currentPage === pages.length);

        // Ajoute la classe 'active' au bouton de la page affichée, l'enlève pour les autres
        paginationNumbers.forEach((btn) => {
            btn.classList.toggle('active', parseInt(btn.dataset.page, 10) === currentPage);
        });
    }

    /**
     * Ajoute un écouteur de clic sur chacun des boutons de pagination
     * pour afficher la page correspondante
     */
    paginationNumbers.forEach((btn) => {
        btn.addEventListener('click', () => {
            const page = parseInt(btn.dataset.page, 10);
            showPage(page);
        });
    });

    /**
     * Bouton "Précédent" - Affiche la page précédente si possible
     */
    prevButton.addEventListener('click', () => {
        if (currentPage > 1) {
            showPage(currentPage - 1);
        }
    });

    /**
     * Bouton "Suivant" - Affiche la page suivante si possible
     */
    nextButton.addEventListener('click', () => {
        if (currentPage < pages.length) {
            showPage(currentPage + 1);
        }
    });

    // Au chargement, on affiche toujours la page 1
    showPage(1);
});
