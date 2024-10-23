package bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import dao.JogoDAO;
import entity.Jogo;

@ManagedBean
@ViewScoped
public class JogoBean {

    private Jogo jogo = new Jogo();
    private List<Jogo> jogos = new ArrayList<Jogo>();
    private int maiorValor;

    public Jogo getJogo() {
        return jogo;
    }

    public void setJogo(Jogo jogo) {
        this.jogo = jogo;
    }

    public List<Jogo> getJogos() {
    	jogos = JogoDAO.listAll();
		return jogos;
	}

	public void setJogos(List<Jogo> jogos) {
		this.jogos = jogos;
	}

	public int getMaiorValor() {
        return maiorValor;
    }


    public String salvar() {
        jogo.setDataCadastro(new Date());
        jogo.setNumeroSorteado(new Random().nextInt(10) + 1); 
        
        JogoDAO.save(jogo);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Jogo salvo com sucesso!"));
        jogo = new Jogo();
    
        return null;
    }
    
    public void prepararEdicao(Jogo jogoSelecionado) {
    	this.jogo = jogoSelecionado;
    }
    
    public String editar() {
    	if (jogo != null) {
            JogoDAO.update(jogo);
            jogos = JogoDAO.listAll();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Jogo atualizado com sucesso!"));
            return null;
    	}
    	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Erro ao atualizar jogo!"));
    	return null;
    }

    public String excluir() {
        if (jogo != null) {
            JogoDAO.delete(jogo);
            jogos = JogoDAO.listAll();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Jogo deletado com sucesso!"));
            return null;
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Erro ao deletar jogo!"));
        return null;
    }

    public void calcularMaior() {
        if (jogo != null) {
        	Integer primeiraComparacao = Math.max(jogo.getV1(), jogo.getV2());
        	Integer segundaComparacao = Math.max(primeiraComparacao, jogo.getV3());
        	Integer terceiraComparacao = Math.max(segundaComparacao, jogo.getV4());
        	maiorValor = Math.max(terceiraComparacao, jogo.getV5());
        }
    }
}
