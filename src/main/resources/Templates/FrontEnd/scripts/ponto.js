document.addEventListener('DOMContentLoaded', (event) => {
    const pontoBtn = document.getElementById('pontoBtn');
    const registrosUl = document.getElementById('registros');

    function carregarRegistros() {
        const registros = JSON.parse(localStorage.getItem('registros')) || [];
        registrosUl.innerHTML = '';
        registros.forEach((registro) => {
            const li = document.createElement('li');
            li.textContent = registro;
            registrosUl.appendChild(li);
        });
    }

    pontoBtn.addEventListener('click', () => {
        const agora = new Date();
        const registro = `Ponto batido em: ${agora.toLocaleString()}`;
        let registros = JSON.parse(localStorage.getItem('registros')) || [];
        registros.push(registro);
        localStorage.setItem('registros', JSON.stringify(registros));
        carregarRegistros();
    });

    carregarRegistros();
});
