/*
 * package com.guilherme.estoque;
 * 
 * import static org.junit.Assert.assertEquals; import static
 * org.junit.Assert.assertThat; import static org.junit.Assert.assertTrue;
 * 
 * import java.io.IOException; import java.util.ArrayList; import
 * java.util.Arrays; import java.util.HashMap; import java.util.HashSet; import
 * java.util.List; import java.util.Map; import java.util.Set;
 * 
 * import org.junit.Before; import org.junit.Test; import
 * org.junit.runner.RunWith; import org.junit.runners.JUnit4; import
 * org.junit.matchers.JUnitMatchers;
 * 
 * import com.fasterxml.jackson.core.JsonProcessingException; import
 * com.fasterxml.jackson.databind.ObjectMapper; import
 * com.guilherme.estoque.model.Estoque; import
 * com.guilherme.estoque.model.EstoqueProduto; import
 * com.guilherme.estoque.model.OrdemEntrega; import
 * com.guilherme.estoque.model.Produto; import
 * com.guilherme.estoque.model.ProdutoOrdemEntrega; import
 * 
 * 
 * @RunWith(JUnit4.class) public class SubclassesSerialization {
 * 
 * @Test public void whenSerializingPolymorphic_thenCorrect() throws
 * JsonProcessingException { Produto prodToJson = new Produto(); Estoque
 * estoqueA = new Estoque("Estoque A"); Estoque estoqueB = new
 * Estoque("Estoque B");
 * 
 * 
 * 
 * String result = new ObjectMapper().writeValueAsString(prodToJson);
 * 
 * //System.out.print(result); assertTrue(result.contains("estoque"));
 * 
 * }
 * 
 * @Test public void whenDeserializingPolymorphic_thenCorret() throws
 * IOException {
 * 
 * Produto prodToJson; Estoque estoqueA = new Estoque("Estoque A"); Estoque
 * estoqueB = new Estoque("Estoque B");
 * 
 * prodToJson = new Produto("Maquininha A", 20, new EstoqueProduto(estoqueA,
 * 10), new EstoqueProduto(estoqueB, 15));
 * 
 * String result = new ObjectMapper().writeValueAsString(prodToJson);
 * //System.out.print(result); Produto funcFromJson = new
 * ObjectMapper().readerFor(Produto.class).readValue(result); for(EstoqueProduto
 * estoque : funcFromJson.getEstoqueProduto())
 * System.out.print(estoque.getQuantidade());
 * 
 * }
 * 
 * @Test public void whenSerializingPolymorphicDeliveryOrder_thenCorrect()
 * throws IOException {
 * 
 * Produto produto = new Produto("Maquininha A", 20); Produto produto1 = new
 * Produto("Maquininha B", 12); OrdemEntrega ordemEntrega = new OrdemEntrega();
 * = new ProdutoOrdemEntrega(); produtoOrdemEntrega.setDesconto(0);
 * produtoOrdemEntrega.setProduto(produto);
 * produtoOrdemEntrega.setQuantidade(20);
 * produtoOrdemEntrega.setOrdemEntrega(ordemEntrega); ProdutoOrdemEntrega
 * produtoOrdemEntrega1 = new ProdutoOrdemEntrega();
 * produtoOrdemEntrega1.setDesconto(0);
 * produtoOrdemEntrega1.setProduto(produto1);
 * produtoOrdemEntrega1.setQuantidade(10);
 * produtoOrdemEntrega1.setOrdemEntrega(ordemEntrega);
 * ordemEntrega.setProdutoOrdemEntrega(produtoOrdemEntrega,produtoOrdemEntrega1)
 * ; String result = new ObjectMapper().writeValueAsString(ordemEntrega);
 * System.out.print(result);
 * 
 * } }
 */