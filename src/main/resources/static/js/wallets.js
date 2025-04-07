let currentIndex = 0;

function moveCarousel(direction) {
    const carousel = document.querySelector('.carousel');
    const walletCards = document.querySelectorAll('.wallet-card');
    const totalCards = walletCards.length;

    if (direction === 'next') {
        currentIndex = (currentIndex + 1) % totalCards;
    } else if (direction === 'prev') {
        currentIndex = (currentIndex - 1 + totalCards) % totalCards;
    }

    const newTransformValue = -(currentIndex * (walletCards[0].offsetWidth + 30)); // Adjust for margin
    carousel.style.transform = `translateX(${newTransformValue}px)`;
}
