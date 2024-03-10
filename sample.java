// Project.java
public class Project {
    
    
    private Long id;
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;


}

public interface ProjectRepository extends JpaRepository<Project, Long>
    {
}

public class ProjectService {
    private ProjectRepository projectRepository;

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Project getProjectById(Long id) {
        return projectRepository.findById(id).orElse(null);
    }

    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    public Project updateProject(Long id, Project updatedProject) {
        if (projectRepository.existsById(id)) {
            updatedProject.setId(id);
            return projectRepository.save(updatedProject);
        }
        return null;
    }

    public boolean deleteProject(Long id) {
        if (projectRepository.existsById(id)) {
            projectRepository.deleteById(id);
            return true;
        }
        return false;
    }
}


public class ProjectController {
   
    private ProjectService projectService;

    public List<Project> getAllProjects() {
        return projectService.getAllProjects();
    }

    public Project getProjectById(@PathVariable Long id) {
        return projectService.getProjectById(id);
    }

    public Project createProject(@RequestBody Project project) {
        return projectService.createProject(project);
    }

    public Project updateProject(@PathVariable Long id, @RequestBody Project updatedProject) {
        return projectService.updateProject(id, updatedProject);
    }

    public ResponseEntity<String> deleteProject(@PathVariable Long id) {
        boolean deleted = projectService.deleteProject(id);
        return deleted ? ResponseEntity.ok("Project deleted") : ResponseEntity.notFound().build();
    }
}
