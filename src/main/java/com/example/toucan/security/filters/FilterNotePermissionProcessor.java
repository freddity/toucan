package com.example.toucan.security.filters;

import com.example.toucan.model.entity.EntityNote;
import com.example.toucan.repository.RepositoryNote;
import com.example.toucan.repository.RepositoryUser;
import com.example.toucan.service.notedetails.NoteDetailsService;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

//@Order(Ordered.LOWEST_PRECEDENCE-9)
public class FilterNotePermissionProcessor extends OncePerRequestFilter {

    private final NoteDetailsService noteDetailsService;
    private final RepositoryNote repositoryNote;
    private final UserDetailsService userDetailsService;
    private final RepositoryUser repositoryUser;

    public FilterNotePermissionProcessor(NoteDetailsService noteDetailsService, RepositoryNote repositoryNote,
                                         UserDetailsService userDetailsService, RepositoryUser repositoryUser) {
        this.noteDetailsService = noteDetailsService;
        this.repositoryNote = repositoryNote;
        this.userDetailsService = userDetailsService;
        this.repositoryUser = repositoryUser;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        System.out.println("PERMM");

        List path = Arrays.asList(request.getServletPath().split("/"));
        String pathUsername = (String) path.get(path.size()-2);
        String pathNoteUUIDString = (String) path.get(path.size()-1);
        UUID pathNoteUUID = UUID.fromString(pathNoteUUIDString);

        List<EntityNote> notes = repositoryUser.findByUsername(pathUsername).getNoteList();
        System.out.println(notes.toString());
        for (EntityNote e : notes) {
            if (pathNoteUUID.equals(e.getUuid())) {
                filterChain.doFilter(request, response);
                return;
            }
        }

        if (Objects.isNull(repositoryNote.findByUuid(pathNoteUUID))) {
            response.sendError(404, "Note with ID " + pathNoteUUID + " does not exist.");
            return;
        } else if (Objects.nonNull(repositoryNote.findByUuid(pathNoteUUID))) {
            response.sendError(403, "You are not an owner of note with ID " + pathNoteUUID + ".");
            return;
        }

        response.sendError(400);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String fullPath = request.getServletPath();
        List servletPath = Arrays.asList(request.getServletPath().split("/"));
        String lastFromPath = (String) servletPath.get(servletPath.size()-1);

        try {
            UUID uuid = UUID.fromString(lastFromPath);
        } catch (IllegalArgumentException e) {
            return true;
        }

        return fullPath.startsWith("/auth") && lastFromPath.equals("thumbnails") && lastFromPath.equals("create");
    }
}
