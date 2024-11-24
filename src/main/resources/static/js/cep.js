document.addEventListener('DOMContentLoaded', () => {
  const cepInput = document.getElementById('cep');
  const stateInput = document.getElementById('state');
  const cityInput = document.getElementById('city');
  const addressInput = document.getElementById('address');

  let debounceTimeout;

  const fetchAddress = (cep) => {
    fetch(`https://viacep.com.br/ws/${cep}/json/`)
      .then((response) => {
        if (!response.ok) {
          throw new Error('Não foi possível consultar o CEP.');
        }
        return response.json();
      })
      .then((data) => {
        if (data.erro) {
          throw new Error('CEP não encontrado ou inválido.');
        }
        stateInput.value = data.uf || '';
        cityInput.value = data.localidade || '';
        addressInput.value = data.logradouro || '';
      })
      .catch((error) => {
        alert(error.message);
        stateInput.value = '';
        cityInput.value = '';
        addressInput.value = '';
      });
  };

  cepInput.addEventListener('input', () => {
    clearTimeout(debounceTimeout);

    const cep = cepInput.value.replace(/\D/g, '');
    if (cep.length === 8) {
      debounceTimeout = setTimeout(() => {
        fetchAddress(cep);
      }, 300);
    }
  });
});
