const urlAPIPecas = 'http://localhost:8080/pecas';
const urlAPIEstoques = 'http://localhost:8080/estoques';


/**
 * Função que permite motrar e ocultar seção especificada do HTML.
 * @param {string} sectionId - O ID da seção a ser exibida.
 */
function showSection(sectionId) {
    document.querySelectorAll('.section-group').forEach(section => {
        section.style.display = 'none';
    });
    document.getElementById(sectionId).style.display = 'block';
}

/** PEÇAS  */

/**
 * Função para limpar os campos do formulário
 */
function limparFormulario() {
    document.getElementById('nome').value = '';
    document.getElementById('descricao').value = '';
    document.getElementById('quantidade').value = '';
}

/**
 * Função para limpar os campos do formulário de pesquisa de peça
 */
function limparPesquisa() {
    document.getElementById('id_peca').value = '';
    listarPecas();
}

/**
 * Função para limpar os campos do formulário
 */
function limparFormularioEdicaoModal() {
    document.getElementById('novo-nome').value = '';
    document.getElementById('nova-descricao').value = '';
    document.getElementById('nova-quantidade').value = '';
}

/**
 * Carregar a lista de peças ao carregar a página
 */
document.addEventListener('DOMContentLoaded', listarPecas);

/**
 * Função para fechar a modal de edição
 */
function fecharModal() {
    document.getElementById("form-peca-modal").style.display = 'none';
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
            const listaPecas = document.getElementById('form-peca-list-tuples');
            listaPecas.innerHTML = '';

            if (data.length === 0) {
                listaPecas.innerHTML = '<tr><td colspan="7">Nenhuma peça cadastrada.</td></tr>';
                return;
            }

            // deve corresponder ao formato de atributos do backend (java)
            data.forEach(peca => {                
                const row = document.createElement('tr');
                row.innerHTML = `
                <td>${peca.idPeca}</td>
                <td>${peca.nome}</td>
                <td>${peca.descricao}</td>
                <td>${peca.quantidadePecas}</td>
                <td>
                    <button class="btn-editar" onclick="abrirModalEdicaoPeca(${peca.idPeca})">Editar</button>
                    <button class="btn-excluir" onclick="excluirPeca(${peca.idPeca})">Excluir</button>
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
 * Função para criar peça 
 */
function adicionarPeca() {
    const nome = document.getElementById('nome').value;
    const descricao = document.getElementById('descricao').value;
    const quantidade = parseInt(document.getElementById('quantidade').value);
    
    const novaPeca = {
        nome: nome,
        descricao: descricao,
        quantidadePecas: quantidade
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
 * Função para abrir o modal de edição e carregar os dados da peça 
 */
function abrirModalEdicaoPeca(idPeca) {
    fetch(`${urlAPIPecas}/${idPeca}`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Erro ao obter dados da peça');
            }
            return response.json();
        })
        .then(data => {
            document.getElementById('novo-nome').value = data.nome;
            document.getElementById('nova-descricao').value = data.descricao;
            document.getElementById('nova-quantidade').value = data.quantidadePecas;
            document.getElementById('form-peca-modal').style.display = 'block';

            const salvarBtn = document.querySelector('#form-peca-modal button[onclick="salvarEdicaoPeca()"]');
            salvarBtn.setAttribute('data-id', idPeca);  // Salva o ID da peça no botão de salvar
        })
        .catch(error => {
            console.error('Erro:', error);
            alert('Erro ao obter dados da peça');
        });
}

/**
 * Função para salvar edição da peça
 */
function salvarEdicaoPeca() {
    const idPeca = document.querySelector('#form-peca-modal button[onclick="salvarEdicaoPeca()"]').getAttribute('data-id');    
    const novo_nome = document.getElementById('novo-nome').value;
    const nova_descricao = document.getElementById('nova-descricao').value;
    const nova_quantidade = parseInt(document.getElementById('nova-quantidade').value);

    if (!novo_nome.trim() || !nova_descricao.trim() || isNaN(nova_quantidade) || nova_quantidade <= 0) {
        alert('Todos os campos são obrigatórios e devem conter valores válidos.');
        return;
    }

    const pecaAtualizada = {
        nome: novo_nome,
        descricao: nova_descricao,
        quantidadePecas: nova_quantidade
    };

    fetch(`${urlAPIPecas}/${idPeca}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(pecaAtualizada)
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Erro ao atualizar peça');
        }
        return response.json();
    })
    .then(data => {
        alert("Peça atualizada com sucesso!");
        fecharModal();
        listarPecas();
    })
    .catch(error => {
        console.error('Erro:', error);
        alert('Erro ao atualizar peça');
    });
}

/**
 * Função para excluir peça 
 */
function excluirPeca(idPeca) {
    if (confirm("Confirma a exclusão desta peça?")) {
        fetch(`${urlAPIPecas}/${idPeca}`, {
            method: 'DELETE'
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Erro ao excluir peça');
            }
            listarPecas(); // Atualiza a lista de peças após excluir
        })
        .catch(error => {
            console.error('Erro:', error);
            alert('Erro ao excluir peça');
        });
    } 
}

/**
 * Função para peça por ID
 */
function pesquisarPeca() {
    const idPeca = document.getElementById('id_peca').value;
    
    if (!idPeca || idPeca <= 0) {
        alert("Favor inserir o ID da peça.");
        return;
    }

    fetch(`${urlAPIPecas}/${idPeca}`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Peça não encontrada');
            }
            return response.json();
        })
        .then(data => {
            const resultadoPesquisaId = document.getElementById('form-peca-list-tuples');
            resultadoPesquisaId.innerHTML = `            
            <tr>
                <td>${data.idPeca}</td>
                <td>${data.nome}</td>
                <td>${data.descricao}</td>
                <td>${data.quantidadePecas}</td>
                <td>
                    <button onclick="abrirModalEdicaoPeca(${data.idPeca})">Editar</button>
                    <button onclick="excluirPeca(${data.idPeca})">Excluir</button>                   
                </td>
            </tr>
            `;
        })
        .catch(error => {
            console.error('Erro:', error);
            alert('Erro ao pesquisar peça');
            const resultadoPesquisaId = document.getElementById('form-peca-list-tuples');
            resultadoPesquisaId.innerHTML = ''; // Limpa o resultado anterior, se houver
        });
}

/** ESTOQUE */

/**
 * Função para limpar os campos do formulário estoque
 */
function limparFormularioEstoque() {
    document.getElementById('id_peca_estoque').value = '';
    document.getElementById('quantidade_estoque').value = '';
}


/**
 * Função para limpar os campos do formulário de pesquisa de estoque
 */
function limparPesquisaEstoque() {
    document.getElementById('id_estoque').value = '';
    listarPecas();
}

/**
 * Função para limpar os campos do formulário estoque
 */
function limparFormularioEdicaoModalEstoque() {
    document.getElementById('novo-id_peca').value = '';
    document.getElementById('nova-quantidade-estoque').value = '';
}

/**
 * Carregar a lista de peças ao carregar a página
 */
document.addEventListener('DOMContentLoaded', listarEstoques);

/**
 * Função para criar estoque 
 */
function adicionarEstoque() {
    const idPeca = document.getElementById('id_peca_estoque').value;
    const quantidadeDisponivel = parseInt(document.getElementById('quantidade_estoque').value);

    const novoEstoque = {
        peca: idPeca,
        quantidadeDisponivel: quantidadeDisponivel
    };

    fetch(`${urlAPIEstoques}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(novoEstoque)
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Erro ao adicionar estoque');
            }
            return response.json();
        })
        .then(data => {
            alert("Estoque adicionado com sucesso!");
            console.log(data); // opcional: exibe o novo estoque adicionado
            limparFormulario();
            listarEstoques(); // Atualiza a lista de estoque após adicionar
        })
        .catch(error => {
            console.error('Erro:', error);
            alert('Erro ao adicionar estoque');
        });
}

/**
* Função para listar todas as peças cadastradas.
*/
function listarEstoques() {
    fetch(`${urlAPIEstoques}`)
        .then(response => {
            if (!response.ok) {
                if (response.status === 404) {
                    // Se o status for 404, significa que não há peças
                    return [];
                } else {
                    throw new Error('Erro ao listar estoques');
                }
            }
            return response.json();
        })
        .then(data => {
            const listaEstoques = document.getElementById('form-estoque-list-tuples');
            listaEstoques.innerHTML = '';

            if (data.length === 0) {
                listaEstoques.innerHTML = '<tr><td colspan="7">Nenhum estoque cadastrado.</td></tr>';
                return;
            }

            // deve corresponder ao formato de atributos do backend (java)
            data.forEach(estoque => {                
                const row = document.createElement('tr');
                row.innerHTML = `
                <td>${estoque.idEstoque}</td>
                <td>${estoque.quantidadeDisponivel}</td>
                <td>${estoque.idPeca}</td>
                <td>
                    <button class="btn-editar" onclick="abrirModalEdicaoPeca(${estoque.idEstoque})">Editar</button>
                    <button class="btn-excluir" onclick="excluirPeca(${estoque.idEstoque})">Excluir</button>
                </td>
            `;
            listaEstoques.appendChild(row);
            });
        })
        .catch(error => {
            console.error('Erro:', error);
            alert('Erro ao listar estoques');
        });
}