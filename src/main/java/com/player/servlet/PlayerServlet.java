package com.player.servlet;

import com.player.dao.PlayerDAO;
import com.player.entity.Player;
import com.player.entity.Indexer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/player")
public class PlayerServlet extends HttpServlet {
    
    private PlayerDAO playerDAO;
    
    @Override
    public void init() throws ServletException {
        playerDAO = new PlayerDAO();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        
        if (action == null) {
            action = "list";
        }
        
        switch (action) {
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                deletePlayer(request, response);
                break;
            default:
                listPlayers(request, response);
                break;
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        
        if ("update".equals(action)) {
            updatePlayer(request, response);
        } else {
            createPlayer(request, response);
        }
    }
    
    private void listPlayers(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Player> players = playerDAO.findAll();
        List<Indexer> indexers = playerDAO.findAllIndexers();
        
        request.setAttribute("players", players);
        request.setAttribute("indexers", indexers);
        request.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(request, response);
    }
    
    private void createPlayer(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String name = request.getParameter("name");
        String fullName = request.getParameter("fullName");
        String age = request.getParameter("age");
        String indexIdStr = request.getParameter("indexId");
        
        // Validation
        String error = validatePlayer(name, fullName, age, indexIdStr);
        
        if (error != null) {
            request.setAttribute("error", error);
            listPlayers(request, response);
            return;
        }
        
        Integer indexId = Integer.parseInt(indexIdStr);
        Indexer indexer = playerDAO.findIndexerById(indexId);
        
        Player player = new Player(name, fullName, age, indexer);
        playerDAO.create(player);
        
        response.sendRedirect("player");
    }
    
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Integer playerId = Integer.parseInt(request.getParameter("id"));
        Player player = playerDAO.findById(playerId);
        List<Indexer> indexers = playerDAO.findAllIndexers();
        
        request.setAttribute("player", player);
        request.setAttribute("indexers", indexers);
        request.getRequestDispatcher("/WEB-INF/views/edit.jsp").forward(request, response);
    }
    
    private void updatePlayer(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String playerIdStr = request.getParameter("playerId");
        String name = request.getParameter("name");
        String fullName = request.getParameter("fullName");
        String age = request.getParameter("age");
        String indexIdStr = request.getParameter("indexId");
        
        // Validation
        String error = validatePlayer(name, fullName, age, indexIdStr);
        
        if (error != null) {
            request.setAttribute("error", error);
            showEditForm(request, response);
            return;
        }
        
        Integer playerId = Integer.parseInt(playerIdStr);
        Integer indexId = Integer.parseInt(indexIdStr);
        
        Player player = playerDAO.findById(playerId);
        Indexer indexer = playerDAO.findIndexerById(indexId);
        
        player.setName(name);
        player.setFullName(fullName);
        player.setAge(age);
        player.setIndexer(indexer);
        
        playerDAO.update(player);
        
        response.sendRedirect("player");
    }
    
    private void deletePlayer(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Integer playerId = Integer.parseInt(request.getParameter("id"));
        playerDAO.delete(playerId);
        
        response.sendRedirect("player");
    }
    
    private String validatePlayer(String name, String fullName, String age, String indexIdStr) {
        if (name == null || name.trim().isEmpty()) {
            return "Name is required";
        }
        if (fullName == null || fullName.trim().isEmpty()) {
            return "Full Name is required";
        }
        if (age == null || age.trim().isEmpty()) {
            return "Age is required";
        }
        if (indexIdStr == null || indexIdStr.trim().isEmpty()) {
            return "Index is required";
        }
        
        try {
            int ageValue = Integer.parseInt(age);
            if (ageValue < 0 || ageValue > 150) {
                return "Age must be between 0 and 150";
            }
        } catch (NumberFormatException e) {
            return "Age must be a valid number";
        }
        
        return null;
    }
    
    @Override
    public void destroy() {
        if (playerDAO != null) {
            playerDAO.close();
        }
    }
}