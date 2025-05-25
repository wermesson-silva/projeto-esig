package br.com.esig.crud.controller;

import br.com.esig.crud.dao.TarefaDAO;
import br.com.esig.crud.model.Prioridade;
import br.com.esig.crud.model.Tarefa;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@Named
@SessionScoped
public class TarefaBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Tarefa tarefa = new Tarefa();
    
    private String filtroResponsavel;
    private Boolean filtroConcluida;
    private Long filtroNumero;
    private String filtroTituloDescricao;
    
    private List<Tarefa> tarefasFiltradas;

    @Inject
    private TarefaDAO tarefaDAO;

    public List<Tarefa> getTarefas() {
    	if (tarefasFiltradas != null) {
            return tarefasFiltradas;
        }
        return tarefaDAO.listar();
    }
    
    public String formTarefa() {
    	this.tarefa = new Tarefa();
    	return "cadastrar?faces-redirect=true";
    }

    public void excluir(Tarefa tarefa) {
        tarefaDAO.excluir(tarefa);
        buscar();
    }

    public String salvar() {
        if (tarefa.getId() == null) {
        	tarefa.setConcluida(false);
            tarefaDAO.salvar(tarefa);
            tarefasFiltradas = null; //limpa a busca filtrada
        } else {
            tarefaDAO.atualizar(tarefa);
            buscar();
        }
        tarefa = new Tarefa(); // limpa formulário
       
        return "listar?faces-redirect=true";
    }

    public String editar(Tarefa tarefa) {
        this.tarefa = tarefa;
        return "cadastrar?faces-redirect=true";
    }
    
    public void concluir(Tarefa tarefa) {
    	tarefa.setConcluida(true);
    	tarefaDAO.atualizar(tarefa);
    	buscar();
    }
    
    public void buscar() {
		tarefasFiltradas = tarefaDAO.filtrar(filtroResponsavel, filtroConcluida, filtroNumero, filtroTituloDescricao);
    }
    
    public void detalhar(Tarefa tarefa) {
    	this.tarefa = tarefa;
    }
    
    public List<Prioridade> getPrioridades() {
        return Arrays.asList(Prioridade.values());
    }

    public Tarefa getTarefa() {
        return tarefa;
    }

    public void setTarefa(Tarefa tarefa) {
        this.tarefa = tarefa;
    }

    public TarefaDAO getTarefaDAO() {
        return tarefaDAO;
    }

    public void setTarefaDAO(TarefaDAO tarefaDAO) {
        this.tarefaDAO = tarefaDAO;
    }

	public String getFiltroResponsavel() {
		return filtroResponsavel;
	}

	public void setFiltroResponsavel(String filtroResponsavel) {
		this.filtroResponsavel = filtroResponsavel;
	}

	public Boolean getFiltroConcluida() {
		return filtroConcluida;
	}

	public void setFiltroConcluida(Boolean filtroConcluida) {
		this.filtroConcluida = filtroConcluida;
	}

	public Long getFiltroNumero() {
		return filtroNumero;
	}

	public void setFiltroNumero(Long filtroNumero) {
		this.filtroNumero = filtroNumero;
	}

	public String getFiltroTituloDescricao() {
		return filtroTituloDescricao;
	}

	public void setFiltroTituloDescricao(String filtroTituloDescricao) {
		this.filtroTituloDescricao = filtroTituloDescricao;
	}

	public int getTotalConcluidas() {
		
		int totalConcluidas = 0;
		
		List<Tarefa> tarefas = tarefaDAO.listar();
    	
    	for(Tarefa posicao: tarefas) {
    		if(posicao.isConcluida()) {
    			totalConcluidas += 1;
    		}
    	}
    	
		return totalConcluidas;
	}

	public int getTotalAndamento() {
		
		int totalAndamento = 0;
		
		List<Tarefa> tarefas = tarefaDAO.listar();
    	
    	for(Tarefa posicao: tarefas) {
    		if(!posicao.isConcluida()) {
    			totalAndamento += 1;
    		}
    	}
		
		return totalAndamento;
	}
    
}
