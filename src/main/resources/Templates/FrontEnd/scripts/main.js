document.addEventListener("DOMContentLoaded", () => {
    const menuToggle = document.getElementById('menuMobile');
    const navLinks = document.querySelector('.nav-links');

    menuToggle.addEventListener('click', () => {
        navLinks.classList.toggle('active');
        menuToggle.classList.toggle('active');
    });
});
