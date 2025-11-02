🧩 Spring Boot JPA & Hibernate Mapping

A compact, educational project on Spring Boot 3.5.5 that demonstrates JPA/Hibernate relationship mappings using the classic trio: Instructor ↔ 
InstructorDetail ↔ Course. It showcases @OneToOne, @OneToMany, @ManyToOne, cascade behaviors, and safe handling of bidirectional associations. 
The app doesn’t expose REST; it runs via a CommandLineRunner and prints the CRUD flow to the console.

🎯 Purpose (What I built)
	•	Demonstrate relationship types (1–1, 1–N, N–1) with a clear, minimal domain.
	•	Show cascade behaviors (ALL, PERSIST, MERGE, DETACH, REFRESH) and how to break associations safely before deletion.
	•	Implement basic save / find / delete operations through a small DAO layer backed by EntityManager.
	•	Emphasize correct setup of both sides of bidirectional relationships (e.g., instructor.add(course) → course.setInstructor(this)).

🧠 What’s Inside
	•	Layers & Packages
	•	entity/ → Instructor, InstructorDetail, Course (JPA entities)
	•	dao/ → AppDAO (interface) and AppDAOImpl (EntityManager-based CRUD)
	•	CruddemoApplication → registers a CommandLineRunner with sample scenarios
	•	Database & Logging
	•	application.properties points to MySQL:
jdbc:mysql://localhost:3306/hb-03-one-to-many with springstudent / springstudent
	•	Hibernate SQL and bind parameter logs are enabled for learning/visibility.

🔗 Relationship Mappings (Summary)
	•	Instructor ↔ InstructorDetail: @OneToOne
	•	On Instructor: @OneToOne(cascade = CascadeType.ALL) + @JoinColumn(name = "instructor_detail_id")
→ Saving an instructor also saves its detail (and vice-versa for other cascaded ops).
	•	On InstructorDetail: @OneToOne(mappedBy = "instructorDetail", cascade = {DETACH, MERGE, PERSIST, REFRESH})
→ No REMOVE here: when deleting a detail, the code breaks the link first and then removes it.
	•	Instructor ↔ Course: @OneToMany / @ManyToOne
	•	On Instructor: @OneToMany(mappedBy = "instructor", cascade = {PERSIST, MERGE, DETACH, REFRESH})
→ Persisting an instructor can persist courses, but REMOVE is not included, so deleting the instructor won’t auto-delete courses.
	•	On Course: @ManyToOne(...) + @JoinColumn(name = "instructor_id")
→ Each course belongs to a single instructor.
	•	Helper Method for Bidirectional Consistency
	•	Instructor#add(Course c) adds the course to the collection and calls c.setInstructor(this) so both sides stay in sync.

🗃️ DAO Operations (observed)
	•	save(Instructor) → Persists the instructor (and, via cascades, its detail and courses as configured).
	•	findInstructorById(int) → Finds an instructor by id.
	•	deleteInstructorById(int) → Deletes an instructor (courses are not auto-removed).
	•	findInstructorDetailById(int) → Finds an instructor detail by id.
	•	deleteInstructorDetailById(int) → Breaks the bidirectional link (detail.getInstructor().setInstructorDetail(null)) and then deletes the detail.

🧪 CommandLineRunner Scenario

Inside CruddemoApplication#commandLineRunner(AppDAO), several demo methods are prepared (commented/uncommented to run):
	•	createInstructor(...), findInstructor(...), deleteInstructor(...)
	•	findInstructorDetail(...), deleteInstructorDetail(...)
	•	And the currently active createInstructorWithCourses(appDAO);

The active scenario creates an Instructor + InstructorDetail + multiple Courses, sets up relationships, and saves them. You’ll see “Saving instructor: …” and the SQL logs in your console.
