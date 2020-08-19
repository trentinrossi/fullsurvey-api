package br.com.fullcustom.fullsurvey.config;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.fullcustom.fullsurvey.model.enumeration.AnswerStatus;
import br.com.fullcustom.fullsurvey.model.enumeration.AnswerType;
import br.com.fullcustom.fullsurvey.model.enumeration.RespondentType;
import br.com.fullcustom.fullsurvey.service.dto.AnswerCheckboxSelectedDTO;
import br.com.fullcustom.fullsurvey.service.dto.AnswerCommentDTO;
import br.com.fullcustom.fullsurvey.service.dto.AnswerDTO;
import br.com.fullcustom.fullsurvey.service.dto.AnswerOptionDTO;
import br.com.fullcustom.fullsurvey.service.dto.CategoryDTO;
import br.com.fullcustom.fullsurvey.service.dto.ConfigurationDTO;
import br.com.fullcustom.fullsurvey.service.dto.CustomerDTO;
import br.com.fullcustom.fullsurvey.service.dto.QuestionDTO;
import br.com.fullcustom.fullsurvey.service.dto.RespondentDTO;
import br.com.fullcustom.fullsurvey.service.dto.SubjectDTO;
import br.com.fullcustom.fullsurvey.service.dto.SurveyDTO;
import br.com.fullcustom.fullsurvey.service.dto.SurveyRespondentDTO;
import br.com.fullcustom.fullsurvey.service.dto.SurveySubjectsDTO;
import br.com.fullcustom.fullsurvey.service.impl.AnswerCheckboxSelectedServiceImpl;
import br.com.fullcustom.fullsurvey.service.impl.AnswerCommentServiceImpl;
import br.com.fullcustom.fullsurvey.service.impl.AnswerOptionServiceImpl;
import br.com.fullcustom.fullsurvey.service.impl.AnswerServiceImpl;
import br.com.fullcustom.fullsurvey.service.impl.CategoryServiceImpl;
import br.com.fullcustom.fullsurvey.service.impl.ConfigurationServiceImpl;
import br.com.fullcustom.fullsurvey.service.impl.CustomerServiceImpl;
import br.com.fullcustom.fullsurvey.service.impl.QuestionServiceImpl;
import br.com.fullcustom.fullsurvey.service.impl.RespondentServiceImpl;
import br.com.fullcustom.fullsurvey.service.impl.SubjectServiceImpl;
import br.com.fullcustom.fullsurvey.service.impl.SurveyRespondentServiceImpl;
import br.com.fullcustom.fullsurvey.service.impl.SurveyServiceImpl;
import br.com.fullcustom.fullsurvey.service.impl.SurveySubjectsServiceImpl;

@Configuration
@Profile({ "dev", "test" })
public class SurveyTestConfig implements CommandLineRunner {

    @Autowired
    CustomerServiceImpl customerService;

    @Autowired
    ConfigurationServiceImpl configurationService;

    @Autowired
    CategoryServiceImpl categoryService;

    @Autowired
    SurveyServiceImpl surveyService;

    @Autowired
    SubjectServiceImpl subjectService;

    @Autowired
    SurveySubjectsServiceImpl surveySubjectsService;

    @Autowired
    QuestionServiceImpl questionService;

    @Autowired
    AnswerOptionServiceImpl answerOptionService;

    @Autowired
    RespondentServiceImpl respondentService;

    @Autowired
    AnswerServiceImpl answerService;

    @Autowired
    AnswerCheckboxSelectedServiceImpl answerCheckboxSelectedService;
    
    @Autowired
    SurveyRespondentServiceImpl surveyRespondentService;
    
    @Autowired
    AnswerCommentServiceImpl answerCommentService;

    @Override
    public void run(String... args) throws Exception {

        // Create a test objects

        // Customer
        CustomerDTO cliente1 = new CustomerDTO("Cliente 1");
        cliente1 = customerService.save(cliente1);
        CustomerDTO cliente2 = new CustomerDTO("Cliente 2");
        cliente2 = customerService.save(cliente2);
        CustomerDTO cliente3 = new CustomerDTO("Cliente 3");
        cliente3 = customerService.save(cliente3);
        CustomerDTO cliente4 = new CustomerDTO("Cliente 4");
        cliente4 = customerService.save(cliente4);
        CustomerDTO cliente5 = new CustomerDTO("Cliente 5");
        cliente5 = customerService.save(cliente5);

        // Configuration
        configurationService.save(new ConfigurationDTO(cliente1.getId()));
        configurationService.save(new ConfigurationDTO(cliente2.getId()));
        configurationService.save(new ConfigurationDTO(cliente3.getId()));
        configurationService.save(new ConfigurationDTO(cliente4.getId()));
        configurationService.save(new ConfigurationDTO(cliente5.getId()));

        // Category
        CategoryDTO category1 = new CategoryDTO("Demissional");
        category1 = categoryService.save(category1);
        CategoryDTO category2 = new CategoryDTO("Pesquisa de Clima");
        category2 = categoryService.save(category2);
        CategoryDTO category3 = new CategoryDTO("Candidatos");
        category3 = categoryService.save(category3);
        CategoryDTO category4 = new CategoryDTO("Pesquisa de Treinamentos");
        category4 = categoryService.save(category4);

        // Survey
        SurveyDTO survey = new SurveyDTO();
        survey.setCustomerId(cliente1.getId());
        survey.setName("Pesquisa de Clima");
        survey.setInitialDate(LocalDate.now());
        survey.setFinalDate(LocalDate.now());
        survey.setDescription("Pesquisa anual da empresa para avaliação do clima organizacional.");
        survey.setExpirationDate(LocalDate.parse("2020-12-31"));
        survey.setObjective("Avaliar o clima organizacional da organização");
        survey.setAnonymous(true);
        survey.setAnswerLink("https://survey.fullcustom.com.br/link");
        survey.setTitleIcon("icon");
        survey = surveyService.save(survey);

        SurveyDTO survey2 = new SurveyDTO();
        survey2.setCustomerId(cliente1.getId());
        survey2.setName("Pesquisa de Refeitório");
        survey2.setInitialDate(LocalDate.now());
        survey2.setFinalDate(LocalDate.now());
        survey2.setDescription("Pesquisa diária para avaliar a qualidade das refeições servidas.");
        survey2.setExpirationDate(LocalDate.parse("2020-12-31"));
        survey2.setObjective("Avaliar a qualidade das refeições servidas");
        survey2.setAnonymous(true);
        survey2.setAnswerLink("https://survey.fullcustom.com.br/link2");
        survey2.setTitleIcon("icon");
        survey2 = surveyService.save(survey2);

        SurveyDTO survey3 = new SurveyDTO();
        survey3.setCustomerId(cliente1.getId());
        survey3.setName("Pesquisa Canditato");
        survey3.setInitialDate(LocalDate.now());
        survey3.setFinalDate(LocalDate.now());
        survey3.setDescription("Pesquisa a cada candidato que é desclassificado.");
        survey3.setExpirationDate(LocalDate.parse("2020-12-31"));
        survey3.setObjective("Avaliar o processo de recrutamento");
        survey3.setAnonymous(false);
        survey3.setAnswerLink("https://survey.fullcustom.com.br/link3");
        survey3.setTitleIcon("icon");
        survey3 = surveyService.save(survey3);

        // Subject
        SubjectDTO subject1 = new SubjectDTO(category1.getId(), "Atendimento");
        subject1 = subjectService.save(subject1);
        SubjectDTO subject2 = new SubjectDTO(category1.getId(), "Gestor");
        subject2 = subjectService.save(subject2);
        SubjectDTO subject3 = new SubjectDTO(category1.getId(), "Colegas de equipe");
        subject3 = subjectService.save(subject3);
        SubjectDTO subject4 = new SubjectDTO(category2.getId(), "Salário");
        subject4 = subjectService.save(subject4);
        SubjectDTO subject5 = new SubjectDTO(category2.getId(), "Ambiente de trabalho");
        subject5 = subjectService.save(subject5);
        SubjectDTO subject6 = new SubjectDTO(category3.getId(), "Atendimento");
        subject6 = subjectService.save(subject6);
        SubjectDTO subject7 = new SubjectDTO(category3.getId(), "Tempo de Resposta");
        subject7 = subjectService.save(subject7);
        SubjectDTO subject8 = new SubjectDTO(category3.getId(), "Sistema");
        subject8 = subjectService.save(subject8);
        SubjectDTO subject9 = new SubjectDTO(category4.getId(), "Avaliador");
        subject9 = subjectService.save(subject9);
        SubjectDTO subject10 = new SubjectDTO(category4.getId(), "Material utilizado");
        subject10 = subjectService.save(subject10);
        SubjectDTO subject11 = new SubjectDTO(category4.getId(), "Sala de Aula");
        subject11 = subjectService.save(subject11);

        // Survey Subjects
        List<UUID> subjects1 = new ArrayList<UUID>();
        subjects1.add(subject1.getId());
        subjects1.add(subject2.getId());
        subjects1.add(subject3.getId());
        subjects1.add(subject4.getId());
        SurveySubjectsDTO surveySubject1 = new SurveySubjectsDTO(survey.getId(), subjects1);
        surveySubjectsService.addSubjectsToSurvey(surveySubject1);

        List<UUID> subjects7 = new ArrayList<UUID>();
        subjects7.add(subject1.getId());
        subjects7.add(subject2.getId());
        subjects7.add(subject3.getId());
        subjects7.add(subject4.getId());
        subjects7.add(subject5.getId());
        subjects7.add(subject6.getId());
        subjects7.add(subject7.getId());
        subjects7.add(subject8.getId());
        SurveySubjectsDTO surveySubject7 = new SurveySubjectsDTO(survey2.getId(), subjects7);
        surveySubjectsService.addSubjectsToSurvey(surveySubject7);

        List<UUID> subjects8 = new ArrayList<UUID>();
        subjects8.add(subject1.getId());
        subjects8.add(subject2.getId());
        SurveySubjectsDTO surveySubject8 = new SurveySubjectsDTO(survey3.getId(), subjects8);
        surveySubjectsService.addSubjectsToSurvey(surveySubject8);

        // Questions
        QuestionDTO question1 = new QuestionDTO(subject1.getId(), AnswerType.RADIO, "Título questão radio", true);
        question1 = questionService.save(question1);
        QuestionDTO question2 = new QuestionDTO(subject1.getId(), AnswerType.CHECKBOX, "Título questão check", false);
        question2 = questionService.save(question2);
        QuestionDTO question3 = new QuestionDTO(subject1.getId(), AnswerType.TEXT, "Título questão texto", false);
        question3 = questionService.save(question3);
        QuestionDTO question4 = new QuestionDTO(subject1.getId(), AnswerType.NPS, "Título questão nps", true);
        question4 = questionService.save(question4);
        QuestionDTO question5 = new QuestionDTO(subject2.getId(), AnswerType.TEXT, "Título questão 1", false);
        question5 = questionService.save(question5);
        QuestionDTO question6 = new QuestionDTO(subject2.getId(), AnswerType.TEXT, "Título questão 1", false);
        question6 = questionService.save(question6);
        QuestionDTO question7 = new QuestionDTO(subject3.getId(), AnswerType.TEXT, "Título questão 1", false);
        question7 = questionService.save(question7);
        QuestionDTO question8 = new QuestionDTO(subject4.getId(), AnswerType.TEXT, "Título questão 1", false);
        question8 = questionService.save(question8);
        QuestionDTO question9 = new QuestionDTO(subject5.getId(), AnswerType.TEXT, "Título questão 1", false);
        question9 = questionService.save(question9);
        QuestionDTO question10 = new QuestionDTO(subject5.getId(), AnswerType.TEXT, "Título questão 1", false);
        question10 = questionService.save(question10);

        // Answer Options
        AnswerOptionDTO question1AnswerOption1 = new AnswerOptionDTO(question1.getId(), "Muito insatisfeito", 0);
        question1AnswerOption1 = answerOptionService.save(question1AnswerOption1);
        AnswerOptionDTO question1AnswerOption2 = new AnswerOptionDTO(question1.getId(), "Insatisfeito", 1);
        question1AnswerOption2 = answerOptionService.save(question1AnswerOption2);
        AnswerOptionDTO question1AnswerOption3 = new AnswerOptionDTO(question1.getId(), "Satisfeito", 2);
        question1AnswerOption3 = answerOptionService.save(question1AnswerOption3);
        AnswerOptionDTO question1AnswerOption4 = new AnswerOptionDTO(question1.getId(), "Ótimo", 3);
        question1AnswerOption4 = answerOptionService.save(question1AnswerOption4);
        AnswerOptionDTO question2AnswerOption1 = new AnswerOptionDTO(question2.getId(), "Carro", 0);
        question2AnswerOption1 = answerOptionService.save(question2AnswerOption1);
        AnswerOptionDTO question2AnswerOption2 = new AnswerOptionDTO(question2.getId(), "Moto", 1);
        question2AnswerOption2 = answerOptionService.save(question2AnswerOption2);
        AnswerOptionDTO question2AnswerOption3 = new AnswerOptionDTO(question2.getId(), "Caminhão", 2);
        question2AnswerOption3 = answerOptionService.save(question2AnswerOption3);
        AnswerOptionDTO question2AnswerOption4 = new AnswerOptionDTO(question2.getId(), "Bicicleta", 3);
        question2AnswerOption4 = answerOptionService.save(question2AnswerOption4);

        // Respondents
        RespondentDTO respondent1 = new RespondentDTO("rodrigotrentinrossi", RespondentType.EMPLOYEE, 34,
                "Rodrigo Trentin Rossi", "059.020.049-69", "rodrigo.rossi@prismainformatica.com.br", "(45) 9 9999-1732",
                LocalDate.now(), LocalDate.now(), LocalDate.now(), "Superior Completo", "Desenvolvimento de Sistemas",
                "Consultor", LocalDate.now(), "Sem justa causa", 252, "PRISMA INFORMATICA LTDA", 1, "FILIAL CASCAVEL",
                LocalDate.now(), "Descrição da Visita");
        respondent1 = respondentService.save(respondent1);

        RespondentDTO respondent2 = new RespondentDTO("alexandremartins", RespondentType.CANDIDATE, 123,
                "Alexandre Martins", "654.321.969-89", "alexandre.m@gmail.com.br", "(48) 98875-9896", LocalDate.now(),
                LocalDate.now(), LocalDate.now(), "Superior Completo", "Auxiliar de produção", "Auxiliar",
                LocalDate.now(), "Sem justa causa", 552, "SENIOR SISTEMAS LTDA", 1, "FILIAL BLUMENAU", LocalDate.now(),
                "Visita na indústria acompanhada do porteiro");
        respondent2 = respondentService.save(respondent2);

        RespondentDTO respondent3 = new RespondentDTO("mariaeduardafreitas", RespondentType.INTERN, 700532,
                "Maria Eduarda Freitas", "442.223.225-96", "maria@empresa.ind.br", "(81) 99985-6698", LocalDate.now(),
                LocalDate.now(), LocalDate.now(), "MBA", "Vendedora Interna", "Consultor", LocalDate.now(),
                "Sem justa causa", 554, "AGRO MAIS LTDA", 1, "FILIAL NORDESTE", LocalDate.now(), "Descrição da Visita");
        respondent3 = respondentService.save(respondent3);

        RespondentDTO respondent4 = new RespondentDTO("antenorribeirodasilva", RespondentType.VISITOR, 400699,
                "Antenor Ribeiro da Silva", "545.566.512-89", "antenor.r.silva223@yahoo.com.br", "+554588987454",
                LocalDate.now(), LocalDate.now(), LocalDate.now(), "Ensino fundamental", "Atendente de farmácia",
                "Atendente I", LocalDate.now(), "Sem justa causa", 1,
                "FARMACIAS ESTRELAS COMERCIO DE MEDICAMENTOS LTDA", 1, "FILIAL NORTE", LocalDate.now(),
                "Descrição da Visita");
        respondent4 = respondentService.save(respondent4);

        // Answers
        AnswerDTO answer1 = new AnswerDTO(survey.getId(), respondent1.getId(), question1.getId(), LocalDate.now(),
                LocalTime.now(), "Resposta texto", 2, 0);
        answer1 = answerService.save(answer1);

        AnswerDTO answer2 = new AnswerDTO(survey.getId(), respondent1.getId(), question2.getId(), LocalDate.now(),
                LocalTime.now(), "Resposta texto", 10, 0);
        answer2 = answerService.save(answer2);

        AnswerDTO answer3 = new AnswerDTO(survey.getId(), respondent1.getId(), question3.getId(), LocalDate.now(),
                LocalTime.now(), "Resposta texto", 1, 0);
        answer3 = answerService.save(answer3);

        AnswerDTO answer4 = new AnswerDTO(survey.getId(), respondent2.getId(), question4.getId(), LocalDate.now(),
                LocalTime.now(), "Resposta texto", 9, 0);
        answer4 = answerService.save(answer4);

        AnswerDTO answer5 = new AnswerDTO(survey.getId(), respondent3.getId(), question5.getId(), LocalDate.now(),
                LocalTime.now(), "Resposta texto", 8, 0);
        answer5 = answerService.save(answer5);

        AnswerDTO answer6 = new AnswerDTO(survey.getId(), respondent4.getId(), question6.getId(), LocalDate.now(),
                LocalTime.now(), "Resposta texto", 5, 0);
        answer6 = answerService.save(answer6);

        // Answer Checkbox Selected        
        answerCheckboxSelectedService.save(new AnswerCheckboxSelectedDTO(answer1.getId(),question1AnswerOption1.getId()));
        answerCheckboxSelectedService.save(new AnswerCheckboxSelectedDTO(answer1.getId(),question1AnswerOption2.getId()));
        answerCheckboxSelectedService.save(new AnswerCheckboxSelectedDTO(answer1.getId(),question1AnswerOption3.getId()));
        answerCheckboxSelectedService.save(new AnswerCheckboxSelectedDTO(answer1.getId(),question1AnswerOption4.getId()));
        answerCheckboxSelectedService.save(new AnswerCheckboxSelectedDTO(answer2.getId(),question1AnswerOption1.getId()));
        answerCheckboxSelectedService.save(new AnswerCheckboxSelectedDTO(answer2.getId(),question1AnswerOption2.getId()));
        answerCheckboxSelectedService.save(new AnswerCheckboxSelectedDTO(answer3.getId(),question1AnswerOption3.getId()));
        answerCheckboxSelectedService.save(new AnswerCheckboxSelectedDTO(answer4.getId(),question1AnswerOption4.getId()));
        answerCheckboxSelectedService.save(new AnswerCheckboxSelectedDTO(answer4.getId(),question1AnswerOption1.getId()));
        answerCheckboxSelectedService.save(new AnswerCheckboxSelectedDTO(answer1.getId(),question1AnswerOption1.getId()));
        
        // Survey Respondent
        surveyRespondentService.save(new SurveyRespondentDTO(survey.getId(), respondent1.getId(), AnswerStatus.NEW, "https://link.com.br"));
        surveyRespondentService.save(new SurveyRespondentDTO(survey.getId(), respondent2.getId(), AnswerStatus.ANSWERED, "https://link.com.br"));
        surveyRespondentService.save(new SurveyRespondentDTO(survey.getId(), respondent3.getId(), AnswerStatus.INCONPLETE, "https://link.com.br"));
        surveyRespondentService.save(new SurveyRespondentDTO(survey.getId(), respondent4.getId(), AnswerStatus.SENT, "https://link.com.br"));        

        // Answer Comment
        answerCommentService.save(new AnswerCommentDTO(survey.getId(), respondent1.getId(), "Comentários positivos", "Comentários negativos"));
        answerCommentService.save(new AnswerCommentDTO(survey2.getId(), respondent2.getId(), "Comentários positivos", "Comentários negativos"));
        answerCommentService.save(new AnswerCommentDTO(survey3.getId(), respondent3.getId(), "Comentários positivos", "Comentários negativos"));
        answerCommentService.save(new AnswerCommentDTO(survey.getId(), respondent4.getId(), "Comentários positivos", "Comentários negativos"));
        answerCommentService.save(new AnswerCommentDTO(survey.getId(), respondent1.getId(), "Comentários positivos", "Comentários negativos"));
        answerCommentService.save(new AnswerCommentDTO(survey.getId(), respondent1.getId(), "Comentários positivos", "Comentários negativos"));
        answerCommentService.save(new AnswerCommentDTO(survey.getId(), respondent1.getId(), "Comentários positivos", "Comentários negativos"));
        answerCommentService.save(new AnswerCommentDTO(survey.getId(), respondent1.getId(), "Comentários positivos", "Comentários negativos"));
    }
}