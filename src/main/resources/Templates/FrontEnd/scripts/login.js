const p = document.getElementById('loginError');

document.getElementById('loginForm').addEventListener('submit', async function (e) {
  e.preventDefault();

  const password = e.target[1].value;
  let cpf = e.target[0].value;

  const encodedCredentials = btoa(`${cpf}:${password}`);


  const response = await fetch(`http://localhost:8080/funcionario/cpf/${cpf}`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
      "Authorization": `Basic ${encodedCredentials}`,
    },
  }).then(response => response.json()).catch(error => console.error('Error:', error));


  if (response && 'nome' in response) {

    const jsonString = JSON.stringify({ cpf: cpf, auth: encodedCredentials });

    localStorage.setItem('user', jsonString);

    window.location.href = "../pages/dash.html";

  } else {
    p.innerText = "CPF ou senha incorretos, por favor tente novamente";

    setTimeout(() => {
      p.innerText = "";
    }, 10000);
  }

  
  // Redirecionar ou realizar outras ações aqui
});


function mascaraCPF(input) {
  let value = input.value.replace(/\D/g, ''); // Remove tudo que não é dígito
  value = value.replace(/(\d{3})(\d)/, '$1.$2'); // Coloca o primeiro ponto
  value = value.replace(/(\d{3})(\d)/, '$1.$2'); // Coloca o segundo ponto
  value = value.replace(/(\d{3})(\d{1,2})$/, '$1-$2'); // Coloca o traço
  input.value = value;
};

