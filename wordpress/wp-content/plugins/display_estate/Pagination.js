document.addEventListener('DOMContentLoaded', function () {
    const voirPlusButton = document.getElementById('voir-plus');
    const filtresSupplementaires = document.getElementById('filtres-supplementaires');

    // Gestion des filtres supplémentaires
    if (voirPlusButton && filtresSupplementaires) {
        voirPlusButton.addEventListener('click', () => {
            // Basculer la visibilité des filtres supplémentaires
            const isVisible = filtresSupplementaires.classList.toggle('hidden');
            filtresSupplementaires.classList.toggle('show', !isVisible);

            // Met à jour le texte du bouton
            voirPlusButton.textContent = isVisible ? 'Voir plus' : 'Voir moins';
        });
    }
    const paginationNumbers = document.querySelectorAll('.pagination-number');
    const prevButton = document.querySelector('.pagination-prev');
    const nextButton = document.querySelector('.pagination-next');

    const pages = document.querySelectorAll('.property-page');

    let currentPage = 1;

    function showPage(page) {
        pages.forEach((p, index) => {
            p.style.display = (index + 1 === page) ? 'block' : 'none';
        });
        currentPage = page;
        updatePaginationButtons();
    }

    function updatePaginationButtons() {
        prevButton.disabled = (currentPage === 1);
        nextButton.disabled = (currentPage === pages.length);
        paginationNumbers.forEach((btn) => {
            btn.classList.toggle('active', parseInt(btn.dataset.page, 10) === currentPage);
        });
    }

    paginationNumbers.forEach((btn) => {
        btn.addEventListener('click', () => {
            const page = parseInt(btn.dataset.page, 10);
            showPage(page);
        });
    });
    prevButton.addEventListener('click', () => {
        if (currentPage > 1) {
            showPage(currentPage - 1);
        }
    });

    nextButton.addEventListener('click', () => {
        if (currentPage < pages.length) {
            showPage(currentPage + 1);
        }
    });

    showPage(1);


});