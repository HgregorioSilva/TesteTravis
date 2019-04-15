
use banco;
create table aln_aluno (
  aln_id bigint not null auto_increment primary key,
  aln_nome varchar(50) not null,
  aln_sobrenome varchar(50) not null,
  aln_idade int not null,
  aln_semestre int not null,
  aln_ra varchar(50) not null,
  aln_sexo varchar(50) not null
);

create table cur_curso (
  cur_id bigint not null auto_increment primary key ,
  cur_nome varchar(50) not null,
  cur_periodo varchar(50) not null,
  cur_materia varchar(50) not null,
  cur_duracao varchar(50) not null,
  aln_id bigint 
);

ALTER TABLE cur_curso ADD FOREIGN KEY FK_ALUNO_ID (aln_id) REFERENCES aln_aluno(aln_id);

