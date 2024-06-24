const urlAPIPecas = 'http://localhost:8080/pecas';
const urlAPIEstoques = 'http://localhost:8080/estoques';
const urlAPIVeiculos = 'http://localhost:8080/veiculos';


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

/*************************************************/
/******************* PEÇAS ***********************/
/*************************************************/

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
 * Função para pesquisar peça por ID
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

/*************************************************/
/**************** ESTOQUE ***********************/
/*************************************************/

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
    const idPeca = parseInt(document.getElementById('id_peca_estoque').value)
    const quantidadeDisponivel = parseInt(document.getElementById('quantidade_estoque').value);

    const novoEstoque = {
        peca: { idPeca: idPeca },
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
* Função para listar todos os estoques cadastrados.
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
                <td>
                    <button class="btn-editar" onclick="abrirModalEdicaoEstoque(${estoque.idEstoque})">Editar</button>
                    <button class="btn-excluir" onclick="excluirEstoque(${estoque.idEstoque})">Excluir</button>
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

/**
 * Função para abrir o modal de edição e carregar os dados do estoque 
 */
function abrirModalEdicaoEstoque(idEstoque) {
    fetch(`${urlAPIEstoques}/${idEstoque}`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Erro ao obter dados do estoque');
            }
            return response.json();
        })
        .then(data => {
            if (!data) {
                throw new Error('Dados do estoque não encontrados');
            }
            document.getElementById('novo_id_peca_estoque').value = data.peca ? data.peca.idPeca : '';
            document.getElementById('nova-quantidade_estoque').value = data.quantidadeDisponivel;
            document.getElementById('form-estoque-modal').style.display = 'block';

            const salvarBtn = document.querySelector('#form-estoque-modal button[onclick="salvarEdicaoEstoque()"]');
            salvarBtn.setAttribute('data-id', idEstoque);  // Salva o ID da estoque no botão de salvar
        })
        .catch(error => {
            console.log(data)
            console.error('Erro:', error);
            console.log(data)
            alert('Erro ao obter dados da estoque');
        });
}

/**
 * Função para salvar edição do estoque
 */
function salvarEdicaoEstoque() {
    const idEstoque = document.querySelector('#form-estoque-modal button[onclick="salvarEdicaoEstoque()"]').getAttribute('data-id');    
    const novo_id_peca_estoque = parseInt(document.getElementById('novo-id_peca_estoque').value);
    const nova_quantidade_estoque=  parseInt(document.getElementById('nova-quantidade_estoque').value);

    if (isNaN(novo_id_peca_estoque) || novo_id_peca_estoque <= 0 || isNaN(nova_quantidade_estoque) || nova_quantidade_estoque <= 0) {
        alert('Todos os campos são obrigatórios e devem conter valores válidos.');
        return;
    }

    const estoqueAtualizado = {
        peca: { idPeca: novo_id_peca_estoque },
        quantidadeDisponivel: nova_quantidade_estoque
    };    

    fetch(`${urlAPIEstoques}/${idEstoque}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(estoqueAtualizado)
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Erro ao atualizar estoque');
        }
        return response.json();
    })
    .then(data => {
        alert("Estoque atualizado com sucesso!");
        fecharModalEstoque();
        listarEstoques();
    })
    .catch(error => {
        console.error('Erro:', error);
        alert('Erro ao atualizar estoque');
    });
}

/**
 * Função para excluir estoque 
 */
function excluirEstoque(idEstoque) {
    if (confirm("Confirma a exclusão deste estoque?")) {
        fetch(`${urlAPIEstoques}/${idEstoque}`, {
            method: 'DELETE'
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Erro ao excluir estoque');
            }
            listarEstoques(); // Atualiza a lista de estoques após excluir
        })
        .catch(error => {
            console.error('Erro:', error);
            alert('Erro ao excluir estoque');
        });
    } 
}

/**
 * Função para pesquisar peça por ID
 */
function pesquisarEstoque() {
    const idEstoque = document.getElementById('id_estoque').value;
    
    if (!idEstoque || idEstoque <= 0) {
        alert("Favor inserir o ID do estoque.");
        return;
    }

    fetch(`${urlAPIEstoques}/${idEstoque}`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Estoque não encontrado');
            }
            return response.json();
        })
        .then(data => {
            const resultadoPesquisaId = document.getElementById('form-estoque-list-tuples');
            resultadoPesquisaId.innerHTML = `            
            <tr>
                <td>${data.idEstoque}</td>
                <td>${data.quantidadeDisponivel}</td>
                <td>
                    <button onclick="abrirModalEdicaoEstoque(${data.idEstoque})">Editar</button>
                    <button onclick="excluirEstoque(${data.idEstoque})">Excluir</button>                   
                </td>
            </tr>
            `;
        })
        .catch(error => {
            console.error('Erro:', error);
            alert('Erro ao pesquisar estoque');
            const resultadoPesquisaId = document.getElementById('form-estoque-list-tuples');
            resultadoPesquisaId.innerHTML = ''; // Limpa o resultado anterior, se houver
        });
}

/****************************************************/
/******************* VEÍCULOS ***********************/
/****************************************************/

/**
 * Função para limpar os campos do formulário
 */
function limparFormularioVeiculo() {
    document.getElementById('chassis').value = '';
    document.getElementById('modelo').value = '';
    document.getElementById('ano_fabricacao').value = '';
    document.getElementById('cor').value = '';
}

/**
 * Função para limpar os campos do formulário de pesquisa de veículo
 */
function limparPesquisaVeiculo() {
    document.getElementById('chassis').value = '';
    listarVeiculos();
}

/**
 * Função para limpar os campos do formulário
 */
function limparFormularioEdicaoModalVeiculo() {
    document.getElementById('novo_chassis').value = '';
    document.getElementById('novo_modelo').value = '';
    document.getElementById('novo_ano_fabricacao').value = '';
    document.getElementById('nova_cor').value = '';
}

/**
 * Carregar a lista de veículos ao carregar a página
 */
document.addEventListener('DOMContentLoaded', listarVeiculos);

/**
 * Função para fechar a modal de edição
 */
function fecharModalVeiculo() {
    document.getElementById("form-veiculo-modal").style.display = 'none';
}


/**
* Função para listar todos os veículos cadastrados.
*/
function listarVeiculos() {
    fetch(`${urlAPIVeiculos}`)
        .then(response => {
            if (!response.ok) {
                if (response.status === 404) {
                    // Se o status for 404, significa que não há peças
                    return [];
                } else {
                    throw new Error('Erro ao listar veiculos');
                }
            }
            return response.json();
        })
        .then(data => {
            const listaVeiculos = document.getElementById('form-veiculo-list-tuples');
            listaVeiculos.innerHTML = '';

            if (data.length === 0) {
                listaVeiculos.innerHTML = '<tr><td colspan="7">Nenhum veículo cadastrado.</td></tr>';
                return;
            }

            // deve corresponder ao formato de atributos do backend (java)
            data.forEach(veiculo => {                
                const row = document.createElement('tr');
                row.innerHTML = `
                <td>${veiculo.chassis}</td>
                <td>${veiculo.modelo}</td>
                <td>${veiculo.anoFabricacao}</td>
                <td>${veiculo.cor}</td>
                <td>
                    <button class="btn-editar" onclick="abrirModalEdicaoVeiculo(${veiculo.chassis})">Editar</button>
                    <button class="btn-excluir" onclick="excluirVeiculo(${veiculo.chassis})">Excluir</button>
                </td>
            `;
                listaVeiculos.appendChild(row);
            });
        })
        .catch(error => {
            console.error('Erro:', error);
            alert('Erro ao listar veiculos');
        });
}

/**
 * Função para criar veiculo 
 */
function adicionarVeiculo() {
    const chassis = document.getElementById('chassis').value;
    const modelo = document.getElementById('modelo').value;
    const ano_fabricacao = parseInt(document.getElementById('ano_fabricacao').value);
    const cor = document.getElementById('cor').value;    
    
    const novoVeiculo = {
        chassis: chassis,
        modelo: modelo,
        anoFabricacao: ano_fabricacao,
        cor: cor
    };

    fetch(`${urlAPIVeiculos}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(novoVeiculo)
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Erro ao adicionar veículo');
            }
            return response.json();
        })
        .then(data => {
            alert("Veículo adicionado com sucesso!");
            console.log(data); // opcional: exibe o novo veiculo adicionada
            limparFormularioVeiculo();
            listarVeiculo(); // Atualiza a lista de veículos após adicionar
        })
        .catch(error => {
            console.error('Erro:', error);
            alert('Erro ao adicionar veículo');
        });
}