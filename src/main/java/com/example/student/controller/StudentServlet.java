package com.example.student.controller;

import com.example.student.model.Student;
import com.example.student.service.ClassService;
import com.example.student.service.StudentService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

@WebServlet(name = "StudentServlet", value = "/student")
public class StudentServlet extends HttpServlet {
    private StudentService studentService = new StudentService ();
    private ClassService classService = new ClassService ();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter ("action");
        if (action == null){
            action = "";
        }
        switch (action){
            case "create":
                showFormCreate(req,resp);
                break;
            case "edit":
                showEditForm(req,resp);
                break;
            case "delete":
                deleteStudent (req,resp);
                break;
            default:
                listStudent(req,resp);
                break;
        }

    }

    private void deleteStudent(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt (req.getParameter ("id"));
        Student student = studentService.findById (id);
        RequestDispatcher requestDispatcher;
        if (student == null){
            requestDispatcher =req.getRequestDispatcher ("/");
        }else {
            studentService.delete (id);
            try {
                resp.sendRedirect ("/student");
            } catch (IOException e) {
                throw new RuntimeException (e);
            }
        }
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt (req.getParameter ("id"));
        Student student = studentService.findById (id);
        RequestDispatcher dispatcher;
        if (student == null){
            dispatcher = req.getRequestDispatcher ("/");
        }else {
            req.setAttribute ("student",student);
            req.setAttribute ("classRoom",classService.findAll ());
            dispatcher = req.getRequestDispatcher ("student/edit.jsp");
        }
        try {
            dispatcher.forward (req,resp);
        } catch (ServletException e) {
            throw new RuntimeException (e);
        } catch (IOException e) {
            throw new RuntimeException (e);
        }
    }

    private void showFormCreate(HttpServletRequest req, HttpServletResponse resp) {
        RequestDispatcher dispatcher = req.getRequestDispatcher("student/create.jsp");
        req.setAttribute ("classRoom",classService.findAll ());
        try {
            dispatcher.forward(req,resp);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void listStudent(HttpServletRequest req, HttpServletResponse resp) {
        List<Student> studentList = studentService.findAll ();
        req.setAttribute ("studentList",studentList);
        req.setAttribute ("class",classService.findAll ());
        RequestDispatcher dispatcher = req.getRequestDispatcher ("student/index.jsp");
        try {
            dispatcher.forward (req,resp);
        } catch (ServletException e) {
            throw new RuntimeException (e);
        } catch (IOException e) {
            throw new RuntimeException (e);
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter ("action");
        if (action == null){
            action = "";
        }
        switch (action){
            case "create":
                createNewStudent(req,resp);
                break;
            case "edit":
                updateStudent(req,resp);
                break;
            case "delete":
                deleteStudent(req,resp);
                break;
            default:
                listStudent (req,resp);
                break;
        }
    }

    private void updateStudent(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt (req.getParameter ("id"));
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        LocalDate dateOfBirth = LocalDate.parse (req.getParameter("date"));
        String address = req.getParameter("address");
        int phoneNumber = Integer.parseInt(req.getParameter("phoneNumber"));
        int classId = Integer.parseInt(req.getParameter("classId"));
        Student student = studentService.findById (id);
        RequestDispatcher dispatcher;
        if (student == null){
            dispatcher = req.getRequestDispatcher ("/");
        }else {
            student.setName (name);
            student.setEmail (email);
            student.setDateOfBirth (dateOfBirth);
            student.setAddress (address);
            student.setPhoneNumber (phoneNumber);
            student.setClassId (classId);
            studentService.update (id,student);
            try {
                resp.sendRedirect ("/student");
            } catch (IOException e) {
                throw new RuntimeException (e);
            }
        }
    }

    private void createNewStudent(HttpServletRequest req, HttpServletResponse resp) {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        LocalDate dateOfBirth = LocalDate.parse (req.getParameter("date"));
        String address = req.getParameter("address");
        int phoneNumber = Integer.parseInt(req.getParameter("phoneNumber"));
        int classId = Integer.parseInt(req.getParameter("classId"));
        Student student = new Student (name,email,dateOfBirth,address,phoneNumber,classId);
        studentService.add(student);
        try {
            resp.sendRedirect ("/student");
        } catch (IOException e) {
            throw new RuntimeException (e);
        }
    }
}
