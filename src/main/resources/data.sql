/**INSERT INTO athletes (athleteId,
                      athleteName,
                      email,
                      phoneNumber,
                      age,
                      startWeight,
                      targetWeight,
                      measurementRightArm,
                      measurementLeftArm,
                      measurementRightLeg,
                      measurementLeftLeg,
                      measurementWaist,
                      trainer)
VALUES (1,'Trevor','email@email.com','067453643','18','80.10','70.40','34','34','60','60','70',null),
       (2,'Alice','email@email.nl','0685674323','18','80.10','70.40','34','34','60','60','70',null),
       (3,'Amanda','email@email.br','0675934521','18','80.10','70.40','34','34','60','60','70',null);


INSERT INTO trainers(personalTrainerId,
                     trainersName,
                     email,
                     phoneNumber,
                     kvkNumber,
                     age,
                     schoolDegree,
                     yearsOfExperience,
                     enrolledAthletes)
VALUES (1,'Teresa','email@trainer.com','0673548234','23542916','18','HBO','3',null),
       (2,'Max','email@trainer.nl','0673548234','23544916','18','HBO','3',null),
       (3,'Teresa','email@trainer.br','0673548234','23242916','18','HBO','3',null);

INSERT INTO exercises(exerciseId,
                      name,
                      targetMuscle,
                      sets,
                      repetitions,
                      weight,
                      workoutRoutineList)
VALUES (1,'exerciseone','back','3','4','23',null),
       (2,'exercisetwo','chest','3','4','23',null),
       (3,'exercisethree','back','3','4','23',null);

INSERT INTO routines(workoutRoutineId,
                     name,
                     description,
                     durationInWeeks,
                     difficultyLevel,
                     exercises)
VALUES ('1','routineone','a routine','3','hard',null),
       ('2','routinetwo','a routine','3','hard',null),
       ('3','routinethree','a routine','3','hard',null);

**/

INSERT INTO users (username, password) VALUES ('gabrielle', '$2a$12$.BVZ/Y4B2IsiCXYQJGc7l.Wv0amfoH/2fgCAn3aIFYdrqBLZ0FEia');
INSERT INTO users (username, password) VALUES ('bas', '$2a$12$1ZqrJ4uNzchupF8YB3xt2.H.qDOwNrYB6.eZjvTCQt/kRpEw8aqLS');
INSERT INTO users (username, password) VALUES ('jan', '$2a$12$t8ebt9ML5Retezcn0Xs4OuD1hobJ8yVjgl7Yp9RXaQ1VVjUXvmoZW');

INSERT INTO authorities (id, username, authority) VALUES (01, 'gabrielle', 'ROLE_ADMIN');
INSERT INTO authorities (id, username, authority) VALUES (02, 'bas', 'ROLE_ATHLETE');
INSERT INTO authorities (id, username, authority) VALUES (03, 'jan', 'ROLE_TRAINER');