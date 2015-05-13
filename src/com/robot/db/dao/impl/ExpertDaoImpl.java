package com.robot.db.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.robot.db.dao.ExpertDao;
import com.robot.entities.Expert;
import com.robot.entities.Field;
import com.robot.entities.Orgnization;
import com.robot.entities.Paper;
import com.robot.entities.Patent;
import com.robot.entities.RelExpertField;
import com.robot.entities.RelExpertFieldId;
import com.robot.entities.RelExpertOrg;
import com.robot.entities.RelExpertOrgId;
import com.robot.entities.RelExpertPaper;
import com.robot.entities.RelExpertPaperId;
import com.robot.entities.RelExpertPatent;
import com.robot.entities.RelExpertPatentId;
import com.robot.entities.RelExpertTopic;
import com.robot.entities.RelExpertTopicId;
import com.robot.entities.Topic;

@Repository("expertDao")
public class ExpertDaoImpl extends BaseDao implements ExpertDao {

	@Override
	public void save(Expert expert) {
		save(expert);
	}

	@Override
	public List<Expert> getByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expert getById(String expertId) {
		return (Expert) getSession().get(Expert.class, expertId);
	}

	@Override
	public void delete(Expert expert) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addField(Expert expert, Field field, int weight) {

		saveOrUpdate(expert);
		saveOrUpdate(field);
		RelExpertFieldId id = new RelExpertFieldId(expert.getExpertId(),
				field.getFieldId());
		RelExpertField relExpertField = new RelExpertField(id, expert, field,
				weight);
		saveOrUpdate(relExpertField);
	}

	@Override
	public void addTopic(Expert expert, Topic topic, int weight) {

		saveOrUpdate(expert);
		saveOrUpdate(topic);
		RelExpertTopicId id = new RelExpertTopicId(expert.getExpertId(),
				topic.getTopicId());
		RelExpertTopic relExpertTopic = new RelExpertTopic(id, expert, topic,
				weight);
		saveOrUpdate(relExpertTopic);

	}

	@Override
	public void addPaper(Expert expert, Paper paper, int authorOrder) {
		saveOrUpdate(expert);
		saveOrUpdate(paper);
		RelExpertPaperId id = new RelExpertPaperId(expert.getExpertId(),
				paper.getPaperId());
		RelExpertPaper relExpertPaper = new RelExpertPaper(id, expert, paper,
				authorOrder);
		saveOrUpdate(relExpertPaper);
	}

	@Override
	public void addPatent(Expert expert, Patent patent, int authorOrder) {
		saveOrUpdate(expert);
		saveOrUpdate(patent);
		RelExpertPatentId id = new RelExpertPatentId(expert.getExpertId(),
				patent.getPatentId());
		RelExpertPatent relExpertPatent = new RelExpertPatent(id, expert,
				patent, authorOrder);
		saveOrUpdate(relExpertPatent);
	}

	@Override
	public void addOrgnization(Expert expert, Orgnization orgnization,
			String job) {
		saveOrUpdate(expert);
		saveOrUpdate(orgnization);
		RelExpertOrgId id = new RelExpertOrgId(expert.getExpertId(),
				orgnization.getOrgId());
		RelExpertOrg relExpertOrg = new RelExpertOrg(id, expert, orgnization,
				job);
		saveOrUpdate(relExpertOrg);
	}

}
