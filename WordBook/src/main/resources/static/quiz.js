
/**
 * クイズの選択肢をクリックしたときの処理
 */
document.addEventListener('DOMContentLoaded', () => {
    const buttons = document.querySelectorAll('.option-button');
    const correctAnswer = document.getElementById('correctAnswer').value;

    buttons.forEach(button => {
        button.addEventListener('click', () => {
            checkAnswer(button, correctAnswer);
        });
    });
});

/**
 * 答え合わせ
 * @param {*} button 
 * @param {*} correctAnswer 
 */
function checkAnswer(button, correctAnswer) {
    const userAnswer = button.innerText;
    const messageBox = document.getElementById('message'); // メッセージ表示用の要素
    const text = document.getElementById('resultText'); // メッセージ表示用の要素

    if (userAnswer === correctAnswer) {
        button.classList.add('correct');
        messageBox.textContent = "正解！";
    } else {
        button.classList.add('wrong');
        messageBox.textContent = "不正解！";
        text.textContent = "正解は「" + correctAnswer + "」です。";
    }

    // 選択肢をすべて無効化
    document.querySelectorAll('.option-button').forEach(btn => btn.disabled = true);

    // 続けるかの選択肢を画面上に表示
    setTimeout(() => {
        const continueBox = document.getElementById('continue-box');
        continueBox.style.display = 'block';
    }, 100);

}