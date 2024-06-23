const urlAPIPecas = 'http://localhost:8080/pecas';


/**
 * Função que permite motrar e ocultar seção especificada do HTML.
 * @param {string} sectionId - O ID da seção a ser exibida.
 */
function showSection(sectionId) {
    document.querySelectorAll('.section').forEach(section => {
        section.style.display = 'none';
    });
    document.getElementById(sectionId).style.display = 'block';
}

/**
 * Função para criar peça 
 */
function adicionarPeca() {
    const nome = document.getElementById('nome').value;
    const descricao = document.getElementById('descricao').value;
    const quantidade = parseInt(document.getElementById('quantidade').value);
    
    const novaPeca = {
        nome: nome,
        descricao: descricao,
        quantidade: quantidade
    };

    fetch(`${urlAPIPecas}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(novaPeca)
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Erro ao adicionar peça');
            }
            return response.json();
        })
        .then(data => {
            alert("Peça adicionada com sucesso!");
            console.log(data); // opcional: exibe o nova peça adicionada
            limparFormulario();
            listarPecas(); // Atualiza a lista de peças após adicionar
        })
        .catch(error => {
            console.error('Erro:', error);
            alert('Erro ao adicionar peça');
        });
}

/**
 * Função para limpar os campos do formulário
 */
function limparFormulario() {
    document.getElementById('nome').value = '';
    document.getElementById('descricao').value = '';
    document.getElementById('quantidade').value = '';
}

/**
* Função para listar todas as peças cadastradas.
*/
function listarPecas() {
    fetch(`${urlAPIPecas}`)
        .then(response => {
            if (!response.ok) {
                if (response.status === 404) {
                    // Se o status for 404, significa que não há peças
                    return [];
                } else {
                    throw new Error('Erro ao listar peças');
                }
            }
            return response.json();
        })
        .then(data => {
            console.log('Dados recebidos do backend:', data); // Log adicional
            const listaPecas = document.getElementById('form-peca-list-tuples');
            listaPecas.innerHTML = '';

            if (data.length === 0) {
                listaPecas.innerHTML = '<tr><td colspan="7">Nenhuma peça cadastrada.</td></tr>';
                return;
            }

            // deve corresponder ao formato de atributos do backend (java)
            data.forEach(peca => {
                console.log('Processando peça:', peca); // Log adicional
                const row = document.createElement('tr');
                row.innerHTML = `
                <td>${peca.idPeca}</td>
                <td>${peca.nome}</td>
                <td>${peca.descricao}</td>
                <td>${peca.quantidadePecas}</td>
                <td>
                    <button onclick="verMais(${peca.idPeca})">Ver mais</button>
                </td>
            `;
                listaPecas.appendChild(row);
            });
        })
        .catch(error => {
            console.error('Erro:', error);
            alert('Erro ao listar peças');
        });
}

/**
 * Carregar a lista de peças ao carregar a página
 */
document.addEventListener('DOMContentLoaded', listarPecas);

