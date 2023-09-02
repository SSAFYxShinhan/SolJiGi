const imgSourcePrefix = './img/friends/';
const imgSource = [
    'front1.png',
    'front2.png',
    'front3.png',
    'front4.png',
    'front5.png',
    'front6.png',
    'front7.png',
    'front8.png',
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

let R, C;
let gameMap = null;
let imgSrcMap = null;
let cardOpened = null;
let selectedIdx = 0;
let matchCardCount = 0;
let allMatchCount = 0;
const selectedCursor = [
    [-1, -1],
    [-1, -1],
];
const gameContent = document.querySelector('.match-card__content');

matchCardGameStart(2, 4);

function matchCardGameStart(row, col) {
    R = row;
    C = col;
    gameContent.innerHTML = '';
    gameMap = Array.from(Array(R), () => new Array(C));
    imgSrcMap = Array.from(Array(R), () => new Array(C));
    cardOpened = Array.from(Array(R), () => new Array(C));
    selectedIdx = 0;
    matchCardCount = 0;
    allMatchCount = (row * col) >> 1;
    constructMap();
    cardAllOpen();
    setTimeout(() => cardAllHide(), 1000);
}

function cardAllOpen() {
    for (let i = 0; i < R; ++i) {
        for (let j = 0; j < C; ++j) {
            cardOpen(i, j);
        }
    }
}

function cardAllHide() {
    for (let i = 0; i < R; ++i) {
        for (let j = 0; j < C; ++j) {
            cardHide(i, j);
        }
    }
}

function cardOpen(x, y) {
    cardOpened[x][y] = true;
    gameMap[x][y].classList.add('match-card__card-open');
}

function cardHide(x, y) {
    cardOpened[x][y] = false;
    gameMap[x][y].classList.remove('match-card__card-open');
}

function checkSelectedPair() {
    let [fi, fj] = selectedCursor[0];
    let [si, sj] = selectedCursor[1];
    if (imgSrcMap[fi][fj] === imgSrcMap[si][sj]) {
        console.log('match');
        selectedIdx = 0;
        ++matchCardCount;
        if (matchCardCount === allMatchCount) {
            setTimeout(() => {
                alert('answer!');
            }, 1000);
        }
    } else {
        console.log('wrong!!!');
        setTimeout(() => {
            cardHide(fi, fj);
            cardHide(si, sj);
            selectedIdx = 0;
        }, 1000);
    }
}

function generateRandomCardPairs(count) {
    let copied = [...imgSource];
    copied.sort(() => Math.random() - 0.5);
    copied = copied.slice(0, count).concat(copied.slice(0, count));
    copied.sort(() => Math.random() - 0.5);
    console.log(copied);
    return copied;
}

function constructMap() {
    const cardImgs = generateRandomCardPairs(allMatchCount);

    for (let i = 0, idx = 0; i < R; ++i) {
        const rowElement = document.createElement('div');
        rowElement.classList.add('match-card__content-row');
        for (let j = 0; j < C; ++j) {
            const flipCardElement = generateCardElement(cardImgs[idx]);
            flipCardElement.addEventListener('click', () => {
                if (!cardOpened[i][j] && selectedIdx < 2) {
                    cardOpen(i, j);
                    selectedCursor[selectedIdx++] = [i, j];
                    if (selectedIdx === 2) {
                        checkSelectedPair();
                    }
                }
            });
            rowElement.appendChild(flipCardElement);
            gameMap[i][j] = flipCardElement;
            imgSrcMap[i][j] = cardImgs[idx];
            ++idx;
        }
        gameContent.appendChild(rowElement);
    }
}

function generateCardElement(fileName) {
    const flipElement = document.createElement('div');
    const cardElement = document.createElement('div');
    const imgElement = document.createElement('img');
    imgElement.setAttribute('src', imgSourcePrefix + fileName);
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
