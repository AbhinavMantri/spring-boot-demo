package com.javaprojects.topic;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicService {
	
	@Autowired
	private TopicRepository topicRepository;
	
//	private List<Topic> topics = new ArrayList<>(Arrays.asList(
//		new Topic(1, "Java", "Core Java"),
//		new Topic(2, "Java.SpringBoot", "Spring Boot Framework"),
//		new Topic(3, "JS", "Javascript"),
//		new Topic(4, "Python", "Core Python"),
//		new Topic(5, "Machine Learnig", "Machine Learning Concepts")
//	));
	
	public List<Topic> getAllTopics() {
		List<Topic> topics = new ArrayList<>();
		
		topicRepository.findAll().forEach(topics :: add);
		
		return topics;
	}
	
	public Topic getTopic(int id) {
		//return topics.stream().filter(t -> t.getId() == id).findFirst().get();
		return topicRepository.findById(id).get();
	}
	
	public void addTopic(Topic topic) {
		List<Topic> topics = getAllTopics();
		
		if(topics.size() > 0) {
			Comparator<Topic> comparator = Comparator.comparing( Topic::getId );
			Topic mTopic = topics.stream().max(comparator).get();
			
			topic.setId(mTopic.getId() + 1);
		} else {	
			topic.setId(1);
		}
		
//		topics.add(topic);
		topicRepository.save(topic);
	}
	
	public void updateTopic(int id, Topic topic) {
		
		topic.setId(id);
		
		List<Topic> topics = getAllTopics();
		
		OptionalInt index = IntStream.range(0, topics.size() -1).filter(i -> topics.get(i).getId() == id).findFirst();
		
		
		topic.setName(topic.getName() != null && topic.getName() != "" ? topic.getName() :  topics.get(index.getAsInt()).getName());
		topic.setDescription(topic.getDescription() != null && topic.getDescription() != "" ? topic.getDescription() :  topics.get(index.getAsInt()).getDescription());
		
		//topics.set(index.getAsInt(), topic);
		topicRepository.save(topic);
	}
	
	public void deleteTopic(int id) {
		// OptionalInt index = IntStream.range(0, topics.size() -1).filter(i -> topics.get(i).getId() == id).findFirst();
		
		// topics.remove(index.getAsInt());
		
		topicRepository.deleteById(id);
	}
}
