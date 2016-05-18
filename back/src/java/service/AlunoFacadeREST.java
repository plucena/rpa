/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package service;

import com.unasp.Aluno;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 *
 * @author plucena
 */
@Stateless
@Path("presenca")
public class AlunoFacadeREST extends AbstractFacade<Aluno> {
    @PersistenceContext(unitName = "AlunoNFCPU")
    private EntityManager em;

    public AlunoFacadeREST() {
        super(Aluno.class);
    }

    

    @PUT
    public void edit(@QueryParam("id") Long id, @QueryParam("nome") String nome, @QueryParam("senha") String senha) {
        if(senha.equals("SENHAUNASP")) {
        Aluno entity = new Aluno();
        entity.setId(id);
        entity.setNome(nome);
        entity.setEntrada(new java.util.Date());
        super.create(entity);
        }        
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({"application/json"})
    public Aluno find(@PathParam("id") Long id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/json"})
    public List<Aluno> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/json"})
    public List<Aluno> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
