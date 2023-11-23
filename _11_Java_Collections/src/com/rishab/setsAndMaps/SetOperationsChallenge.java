package com.rishab.setsAndMaps;

import java.util.*;

enum Priority {HIGH, MEDIUM, LOW}

enum Status {IN_QUEUE, ASSIGNED, IN_PROGRESS}

class Task implements Comparable<Task> {
    private String project;
    private String description;
    private String assignee;
    private Priority priority;
    private Status status;

    public Task(String project, String description, Priority priority) {
        this(project, description, null, priority);
    }

    public Task(String project, String description, String assignee, Priority priority) {
        this(project, description, assignee, priority, assignee == null ? Status.IN_QUEUE : Status.ASSIGNED);
    }

    public Task(String project, String description, String assignee, Priority priority, Status status) {
        this.project = project;
        this.description = description;
        this.assignee = assignee;
        this.priority = priority;
        this.status = status;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "%-20s %-25s %-10s %-10s %s".formatted(project, description, priority, assignee, status);
    }

    // as the task should be unique by project and description, we override equals() and hashCode()

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if (!getProject().equals(task.getProject())) return false;
        return getDescription().equals(task.getDescription());
    }

    @Override
    public int hashCode() {
        int result = getProject().hashCode();
        result = 31 * result + getDescription().hashCode();
        return result;
    }

    @Override
    public int compareTo(Task o) {
        int result = this.project.compareTo(o.project);
        // if the project is the same, then compare the description
        if (result == 0) {
            result = this.description.compareTo(o.description);
        }
        return result;
    }
}

class TaskData {
    private static String tasks = """
        Infrastructure, Logging, High
        Infrastructure, DB Access, Medium
        Infrastructure, Security, High
        Infrastructure, Password Policy, Medium
        Data Design, Task Table, Medium
        Data Design, Employee Table, Medium
        Data Design, Cross Reference Tables, High
        Data Design, Encryption Policy, High
        Data Access, Write Views, Low
        Data Access, Set Up Users, Low
        Data Access, Set Up Access Policy, Low
        """;

    private static String annsTasks = """
        Infrastructure, Security, High, In Progress
        Infrastructure, Password Policy,Medium, In Progress
        Research, Cloud solutions, Medium, In Progress
        Data Design, Encryption Policy, High
        Data Design, Project Table, Medium
        Data Access, Write Views,Low, In Progress
        """;

    private static String bobsTasks = """
        Infrastructure, Security, High, In Progress
        Infrastructure, Password Policy, Medium
        Data Design,Encryption Policy,High
        Data Access,Write Views, Low, In Progress
        """;

    private static String carolsTasks = """
        Infrastructure, Logging, High, In Progress
        Infrastructure, DB Access, Medium
        Infrastructure, Password Policy, Medium
        Data Design, Task Table, High
        Data Access, Write Views, Low
        """;

    public static Set<Task> getTasks(String name) {
        Set<Task> taskSet = new HashSet<>();
        String user = ("ann,bob,carol".contains(name.toLowerCase())) ? name : null;
        String selectedTasks = switch (name.toLowerCase()) {
            case "ann" -> annsTasks;
            case "bob" -> bobsTasks;
            case "carol" -> carolsTasks;
            default -> tasks;
        };

        for (String task : selectedTasks.split("\n")) {
            String[] data = task.split(",");
            Arrays.asList(data).replaceAll(String::trim);

            Status status = (data.length <= 3) ?
                Status.IN_QUEUE :
                Status.valueOf(data[3].toUpperCase().replace(" ", "_"));

            Priority priority = Priority.valueOf(data[2].toUpperCase());
            taskSet.add(new Task(data[0], data[1], user, priority, status));
        }

        return taskSet;
    }
}

public class SetOperationsChallenge {
    public static void main(String[] args) {

        Set<Task> tasks = TaskData.getTasks("all");
        sortAndPrint("All Tasks", tasks);

        Comparator<Task> sortByPriority = Comparator.comparing(Task::getPriority);
        Set<Task> annsTasks = TaskData.getTasks("Ann");
        sortAndPrint("Ann's Tasks", annsTasks, sortByPriority);

        Set<Task> bobsTasks = TaskData.getTasks("bob");
        sortAndPrint("Bob's Tasks", bobsTasks);

        Set<Task> carolsTasks = TaskData.getTasks("carol");
        sortAndPrint("Carol's Tasks", carolsTasks);

        List<Set<Task>> sets = List.of(annsTasks, bobsTasks, carolsTasks);

        Set<Task> assignedTasks = getUnion(sets);
        sortAndPrint("Assigned Tasks", assignedTasks);

        Set<Task> everyTask = getUnion(List.of(tasks, assignedTasks));
        sortAndPrint("The True All Tasks", everyTask);

        Set<Task> missingTasks = getDifference(everyTask, tasks);
        sortAndPrint("Missing Tasks", missingTasks);

        Set<Task> unassignedTasks = getDifference(tasks, assignedTasks);
        sortAndPrint("Unassigned Tasks", unassignedTasks, sortByPriority);

        Set<Task> overlap = getUnion(List.of(
            getIntersection(annsTasks, bobsTasks),
            getIntersection(bobsTasks, carolsTasks),
            getIntersection(annsTasks, carolsTasks)
        ));
        sortAndPrint("Assigned to Multiple", overlap, sortByPriority);

        List<Task> overlapping = new ArrayList<>();
        for (Set<Task> set : sets) {
            Set<Task> dupes = getIntersection(set, overlap);
            overlapping.addAll(dupes);
        }
        Comparator<Task> naturalPriority = sortByPriority.thenComparing(Comparator.naturalOrder());
        sortAndPrint("Overlapping Tasks", overlapping, naturalPriority);

    }

    private static Set<Task> getUnion(List<Set<Task>> sets) {
        Set<Task> union = new HashSet<>();
        for (var set : sets) {
            union.addAll(set);
        }
        return union;
    }

    private static Set<Task> getIntersection(Set<Task> set1, Set<Task> set2) {
        Set<Task> intersection = new HashSet<>(set1);
        intersection.retainAll(set2);
        return intersection;
    }

    private static Set<Task> getDifference(Set<Task> set1, Set<Task> set2) {
        Set<Task> difference = new HashSet<>(set1);
        difference.removeAll(set2);
        return difference;
    }

    private static void sortAndPrint(String header, Collection<Task> collection) {
        // here comparator is null, so the natural ordering of the elements is used, which is defined by the compareTo() method
        // here it will sort by project and then by description
        sortAndPrint(header, collection, null);
    }

    private static void sortAndPrint(String header, Collection<Task> collection, Comparator<Task> comparator) {
        String lineSeparator = "—".repeat(90);
        System.out.println(lineSeparator);
        System.out.println(header);
        System.out.println(lineSeparator);

        List<Task> list = new ArrayList<>(collection);
        list.sort(comparator);
        list.forEach(System.out::println);
    }
}
