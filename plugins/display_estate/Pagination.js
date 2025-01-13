document.addEventListener('DOMContentLoaded', function () {
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
