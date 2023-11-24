/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.notimeforwaste.dao;

import com.notimeforwaste.model.FormaEntrega;
import com.notimeforwaste.model.FormaPagamento;
import com.notimeforwaste.model.Pedido;
import com.notimeforwaste.response.PedidoResponse;

import java.util.List;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

/**
 *
 * @author arthu
 */
@RegisterBeanMapper(Pedido.class)
public interface PedidoDao {

        @GetGeneratedKeys
        @SqlUpdate("INSERT INTO Pedido (idCliente, status, idFormaPagamento, idFormaEntrega, frete, cancelado, idPacote, dtPedido, idEndereco, observacao) "
                        +
                        "VALUES (:idCliente, :status, :idFormaPagamento, :idFormaEntrega, :frete, :cancelado, :idPacote, :dtPedido, :idEndereco, :observacao)")
        int insertWitchIdEndereco(@BindBean Pedido pedido);

               @GetGeneratedKeys
        @SqlUpdate("INSERT INTO Pedido (idCliente, status, idFormaPagamento, idFormaEntrega, frete, cancelado, idPacote, dtPedido, observacao) "
                        +
                        "VALUES (:idCliente, :status, :idFormaPagamento, :idFormaEntrega, :frete, :cancelado, :idPacote, :dtPedido, :observacao)")
        int insertWitchNotIdEndereco(@BindBean Pedido pedido);


        @SqlQuery("select * from Pedido")
        List<Pedido> findAll();

        @SqlQuery("select * from Pedido where idPedido = :idPedido")
        Pedido findById(@Bind("idPedido") int idPedido);

        @SqlQuery("select * from Pedido where idCliente = :idCliente")
        List<Pedido> findByClientId(@Bind("idCliente") int idCliente);

        @SqlQuery("SELECT * FROM Pedido WHERE idPacote IN (SELECT idPacote FROM Pacote WHERE idEmpresa = :idEmpresa)")
        List<Pedido> findByCompanyId(@Bind("idEmpresa") int idEmpresa);

        @SqlUpdate("update Pedido " +
                        " set status = :status, " +
                        "     idFormaPagamento = :idFormaPagamento, " +
                        "     idFormaEntrega = :idFormaEntrega, " +
                        "     frete = :frete, " +
                        "     cancelado = :cancelado, " +
                        "     idPacote = :idPacote, " +
                        "     dtPedido = :dtPedido, " +
                        "     idEndereco = :idEndereco " +
                        " where idPedido = :idPedido;")
        int update(@BindBean Pedido pedido);

        @SqlUpdate("update Pedido " +
                        " set status = :status " +
                        " where idPedido = :idPedido;")
        int updateStatus(@Bind("status") int status, @Bind("idPedido") int idPedido);

        @SqlUpdate("update Pedido " +
                        " set cancelado = :cancelado " +
                        " where idPedido = :idPedido;")
        int cancelar(@Bind("cancelado") Boolean cancelado, @Bind("idPedido") int idPedido);

        @SqlUpdate("delete " +
                        " from Pedido " +
                        " where idPedido = :idPedido;")
        int delete(@Bind("idPedido") int idPedido);

        @SqlQuery("SELECT COUNT(*) FROM Pedido WHERE idPacote = :idPacote")
        int existsByIdPacote(@Bind("idPacote") int idPacote);

        @SqlQuery("SELECT Pedido.idPedido, Pedido.dtPedido, Pedido.status, Pedido.cancelado, Pedido.frete, " +
                        "FormaEntrega.idFormaEntrega, FormaEntrega.nome AS nomeFormaEntrega, FormaPagamento.idFormaPagamento, "
                        +
                        "FormaPagamento.nome AS nomeFormaPagamento, Pacote.idPacote, Pacote.nmPacote, Empresa.idEmpresa, "
                        +
                        "Empresa.nmEmpresa, Pacote.idFoto, Pacote.preco, Cliente.idCliente, Cliente.nmCliente, " +
                        "GROUP_CONCAT(Produto.idProduto) AS idsProdutos, GROUP_CONCAT(Produto.nmProduto) AS produtos " +
                        "FROM Pedido " +
                        "JOIN FormaEntrega ON Pedido.idFormaEntrega = FormaEntrega.idFormaEntrega " +
                        "JOIN FormaPagamento ON Pedido.idFormaPagamento = FormaPagamento.idFormaPagamento " +
                        "JOIN Pacote ON Pedido.idPacote = Pacote.idPacote " +
                        "JOIN Empresa ON Pacote.idEmpresa = Empresa.idEmpresa " +
                        "JOIN Cliente ON Pedido.idCliente = Cliente.idCliente " +
                        "JOIN Produto ON Pacote.idPacote = Produto.idPacote " +
                        "WHERE Pedido.idPedido = :idPedido GROUP BY Pedido.idPedido, Pedido.dtPedido, Pedido.status, Pedido.cancelado, Pedido.frete,"
                        +
                        "FormaEntrega.idFormaEntrega, FormaEntrega.nome," +
                        "FormaPagamento.idFormaPagamento, FormaPagamento.nome," +
                        "Pacote.idPacote, Pacote.nmPacote, Empresa.idEmpresa, Empresa.nmEmpresa, Pacote.idFoto, Pacote.preco,"
                        +
                        "Cliente.idCliente, Cliente.nmCliente;")
        PedidoResponse findResponseByIdPedido(@Bind("idPedido") int idPedido);

}
