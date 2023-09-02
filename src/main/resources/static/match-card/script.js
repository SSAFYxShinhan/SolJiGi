const imgSourcePrefix = './img/friends/';
const imgSource = [
    'front.png',
    // 'SOL.png',
    // 'LAY.png',
    // 'LULULALA.png',
    // 'MOLI.png',
    // 'PLI.png',
    // 'DO.png',
    // 'RE.png',
    // 'MI.png',
    // 'RINO.png',
    // 'SHOO.png',
    // 'PLILAY.png',
    // 'Friends.png',
];

let gameMap = null;
let cardSelected = null;
const gameContent = document.querySelector('.match-card__content');
// gameContent.appendChild(generateCard(11));

matchCardGameStart(2, 2);

function matchCardGameStart(row, col) {
    gameContent.innerHTML = '';
    gameMap = Array.from(Array(row), () => new Array(col));
    cardSelected = Array.from(Array(row), () => new Array(col));
    constructMap(2, 2);
}

function constructMap(row, col) {
    for (let i = 0; i < row; ++i) {
        const rowElement = document.createElement('div');
        rowElement.classList.add('match-card__content-row');
        for (let j = 0; j < col; ++j) {
            const flipCardElement = generateCard(0);
            flipCardElement.addEventListener('click', () => {
                if (!cardSelected[i][j]) {
                    cardSelected[i][j] = true;
                    flipCardElement.classList.add('match-card__card-open');
                } else {
                    cardSelected[i][j] = false;
                    flipCardElement.classList.remove('match-card__card-open');
                }
            });
            rowElement.appendChild(flipCardElement);
            gameMap[i][j] = flipCardElement;
        }
        gameContent.appendChild(rowElement);
    }
}

function generateCard(index) {
    const flipElement = document.createElement('div');
    const cardElement = document.createElement('div');
    const imgElement = document.createElement('img');
    imgElement.setAttribute('src', imgSourcePrefix + imgSource[index]);
    imgElement.classList.add('match-card__card-front');
    const backImgElement = document.createElement('img');
    backImgElement.setAttribute('src', imgSourcePrefix + 'back.png');
    backImgElement.classList.add('match-card__card-back');
    cardElement.appendChild(imgElement);
    cardElement.appendChild(backImgElement);
    cardElement.classList.add('match-card__card');
    flipElement.classList.add('match-card__flip');
    flipElement.appendChild(cardElement);
    return flipElement;
}
