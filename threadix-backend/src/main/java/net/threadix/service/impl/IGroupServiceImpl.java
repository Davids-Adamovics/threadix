package net.threadix.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.threadix.model.Group;
import net.threadix.repo.IGroupRepo;
import net.threadix.service.IGroupService;

@Service
public class IGroupServiceImpl implements IGroupService {

    @Autowired
    private IGroupRepo groupRepo;

    @Override
    public void create(Group group) {
        groupRepo.save(group);
    }

    @Override
    public Group retrieveById(int id) throws Exception {
        if (id < 0)
            throw new Exception("ID jābūt pozitīvam!");

        if (groupRepo.existsById(id)) {
            return groupRepo.findById(id).get();
        } else {
            throw new Exception("Group ar šo id (" + id + ") neeksistē!");
        }
    }

    @Override
    public ArrayList<Group> retrieveAll() throws Exception {
        if (groupRepo.count() == 0)
            throw new Exception("Nav neviena Group!");

        return (ArrayList<Group>) groupRepo.findAll();
    }

    @Override
    public void updateById(int id, Group group) throws Exception {
        Group groupForUpdate = retrieveById(id);

        groupForUpdate.setName(group.getName());
        groupForUpdate.setDescription(group.getDescription());
        groupForUpdate.setMemberCount(group.getMemberCount());

        groupRepo.save(groupForUpdate);
    }

    @Override
    public void deleteById(int id) throws Exception {
        Group groupForDeletion = retrieveById(id);
        groupRepo.delete(groupForDeletion);
    }
}
