import br.com.cod3r.cm.excecao.ExplosaoException;
import br.com.cod3r.cm.modelo.Campo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

public class CampoTeste {

    private Campo campo;

    @BeforeEach
    void iniciarCampo() {
        campo = new Campo(3, 3);
    }

    @Test
    void testeVizinhoDistancia1Esquerda() {
        Campo vizinho = new Campo(3, 2);
        boolean resultado = campo.adicionarVizinho(vizinho);
        assertTrue(resultado);
    }

    @Test
    void testeVizinhoDistancia1Direita() {
        Campo vizinho = new Campo(3, 4);
        boolean resultado = campo.adicionarVizinho(vizinho);
        assertTrue(resultado);
    }

    @Test
    void testeVizinhoDistancia1Cima() {
        Campo vizinho = new Campo(2, 3);
        boolean resultado = campo.adicionarVizinho(vizinho);
        assertTrue(resultado);
    }

    @Test
    void testeVizinhoDistancia1Baixo() {
        Campo vizinho = new Campo(4, 3);
        boolean resultado = campo.adicionarVizinho(vizinho);
        assertTrue(resultado);
    }

    @Test
    void testeVizinhoDistancia2() {
        Campo vizinho = new Campo(2, 2);
        boolean resultado = campo.adicionarVizinho(vizinho);
        assertTrue(resultado);
    }

    @Test
    void testeNaoVizinho() {
        Campo vizinho = new Campo(1, 1);
        boolean resultado = campo.adicionarVizinho(vizinho);
        assertFalse(resultado);
    }

    @Test
    void testeValorPadraoAtributoMarcado() {
        assertFalse(campo.isMarcado());
    }

    @Test
    void testeAlternaMarcacao() {
        campo.alternarMarcacao();
        assertTrue(campo.isMarcado());
    }

    @Test
    void testeAlternaMarcacaoDuasVezes() {
        campo.alternarMarcacao();
        campo.alternarMarcacao();
        assertFalse(campo.isMarcado());
    }

    @Test
    void testeAbrirNaoMinadoNaoMarcado() {
        assertTrue(campo.abrir());
    }

    @Test
    void testeAbrirNaoMinadoMarcado() {
        campo.alternarMarcacao();
        assertFalse(campo.abrir());
    }

    @Test
    void testeAbrirMinadoMarcado() {
        campo.alternarMarcacao();
        campo.minar();
        assertFalse(campo.abrir());
    }

    @Test
    void testeAbrirMinadoNaoMarcado() {
        campo.minar();

        assertThrows(ExplosaoException.class, () -> {
           campo.abrir();
        });
    }

    @Test
    void testeAbrirComVizinhos1() {

        Campo campo11 = new Campo(1, 1);
        Campo campo22 = new Campo(2, 2);
        campo22.adicionarVizinho(campo11);

        campo.adicionarVizinho(campo22);
        campo.abrir();

        assertTrue(campo22.isAberto() && campo11.isAberto());
    }

    @Test
    void testeAbrirComVizinhos2() {

        Campo campo11 = new Campo(1, 1);
        Campo campo12 = new Campo(1, 1);
        campo12.minar();

        Campo campo22 = new Campo(2, 2);
        campo22.adicionarVizinho(campo11);
        campo22.adicionarVizinho(campo12);

        campo.adicionarVizinho(campo22);
        campo.abrir();

        assertTrue(campo22.isAberto() && campo11.isFechado());
    }

    @Test
    void testeObjetivoAlcancadoDesvendado() {
        campo.abrir();
        assertTrue(campo.objetivoAlcancado());
    }

    @Test
    void testeObjetivoAlcancadoProtegido() {
        campo.alternarMarcacao();
        campo.minar();
        campo.abrir();
        assertTrue(campo.objetivoAlcancado());
    }

    @Test
    void testeMinasNaVizinhancaZero() {
        assertEquals(0, campo.minasNaVizinhanca());
    }

    void testeMinasNaVizinhancaMaiorQueZero() {
        Campo c1 = new Campo(2, 2);
        c1.minar();

        campo.adicionarVizinho(c1);

        assertTrue(campo.minasNaVizinhanca() > 0);
    }

    @Test
    void testeToStringX() {
        campo.alternarMarcacao();
        assertEquals("x", campo.toString());
    }

    @Test
    void testeToStringAsterisco() {
        campo.minar();
        assertThrows(ExplosaoException.class, () -> {
            campo.abrir();
        });
        assertEquals("*", campo.toString());
    }

    @Test
    void testeToStringMinasNaVizinhanca() {
        Campo c1 = new Campo(2, 2);
        c1.minar();

        campo.adicionarVizinho(c1);
        campo.abrir();

        assertEquals("1", campo.toString().toString());
    }

    @Test
    void testeToStringAberto() {
        campo.abrir();

        assertEquals(" ", campo.toString());
    }

    @Test
    void testeToStringInterrogacao() {
        assertEquals("?", campo.toString());
    }

    @Test
    void testeReiniciarAberto() {
        campo.abrir();
        campo.reiniciar();
        assertFalse(campo.isAberto());
    }

    @Test
    void testeGetLinha() {
        assertEquals(3, campo.getLinha());
    }

    @Test
    void testeGetColuna() {
        assertEquals(3, campo.getColuna());
    }

}
