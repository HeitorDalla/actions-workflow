package com.heitor;

import com.heitor.model.Endereco;
import com.heitor.model.Usuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        SessionFactory sf = new Configuration()
                .addAnnotatedClass(com.heitor.model.Usuario.class)
                .addAnnotatedClass(com.heitor.model.Endereco.class)
                .addAnnotatedClass(com.heitor.model.Emprestimo.class)
                .addAnnotatedClass(com.heitor.model.Reserva.class)
                .addAnnotatedClass(com.heitor.model.Exemplar.class)
                .addAnnotatedClass(com.heitor.model.ItemEmprestimo.class)
                .addAnnotatedClass(com.heitor.model.Multa.class)
                .addAnnotatedClass(com.heitor.model.Livro.class)
                .addAnnotatedClass(com.heitor.model.LivroAutor.class)
                .addAnnotatedClass(com.heitor.model.LivroCategoria.class)
                .addAnnotatedClass(com.heitor.model.Categoria.class)
                .addAnnotatedClass(com.heitor.model.Autor.class)
                .addAnnotatedClass(com.heitor.model.Editora.class)
                .configure()
                .buildSessionFactory();

        Session session = sf.openSession();

//        // find() - retorna `null` se nao existir
//        Usuario usuario = session.find(Usuario.class, 1);
//        System.out.println(usuario);
//
//        Usuario usuario1 = session.find(Usuario.class, 1);
//        System.out.println(usuario1);

//        // load() - retorna - ObjectNotFoundException - se nao existir
//        Usuario usuario1 = new Usuario();
//        session.load(usuario1, 2L); // passar a classe e a chave primaria
//        System.out.println(usuario1);

//        // HQL
//        String estado = "PR";
//        int id = 1;
//
//        Query query2 = session.createQuery("select logradouro, cidade from Endereco where id = ?1 and estado = ?2");
//
//        query2.setParameter(1, id);
//        query2.setParameter(2, estado);
//
//        List<Endereco> enderecos = query2.getResultList(); // `List<Endereco>` e usado pois tem apenas 1 coluna
//        List<Object[]> logradouroCiddade = query2.getResultList(); // `List<Object[]>` e usados pois tem varias colunas
//
//        System.out.println("Consulta realizada");
//        System.out.println(logradouroCiddade);

//        // Testando cache nivel 2 do hibernate
//        Session s1 = sf.openSession();
//        s1.beginTransaction();
//        Usuario u1 = s1.find(Usuario.class, 1L);
//        s1.getTransaction().commit();
//        s1.close();
//
//        Session s2 = sf.openSession();
//        s2.beginTransaction();
//        Usuario u2 = s2.find(Usuario.class, 1L);
//        s2.getTransaction().commit();

        session.close();
        sf.close();
    }
}