package net.threadix.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.threadix.model.Community;
import net.threadix.repo.ICommunityRepo;
import net.threadix.service.ICommunityService;

@Service
public class ICommunityServiceImpl implements ICommunityService {

    @Autowired
    private ICommunityRepo communityRepo;

    @Override
    public void create(Community community) {
        communityRepo.save(community);
    }

    @Override
    public Community retrieveById(int id) throws Exception {
        if (id < 0)
            throw new Exception("ID jābūt pozitīvam!");

        if (communityRepo.existsById(id)) {
            return communityRepo.findById(id).get();
        } else {
            throw new Exception("Community ar šo id (" + id + ") neeksistē!");
        }
    }

    @Override
    public ArrayList<Community> retrieveAll() throws Exception {
        if (communityRepo.count() == 0)
            throw new Exception("Nav neviena Community!");

        return (ArrayList<Community>) communityRepo.findAll();
    }

    @Override
    public void updateById(int id, Community community) throws Exception {
        Community communityForUpdate = retrieveById(id);

        communityForUpdate.setName(community.getName());
        communityForUpdate.setDescription(community.getDescription());
        communityForUpdate.setVisibility(community.getVisibility());
        communityForUpdate.setMemberCount(community.getMemberCount());

        communityRepo.save(communityForUpdate);
    }

    @Override
    public void deleteById(int id) throws Exception {
        Community communityForDeletion = retrieveById(id);
        communityRepo.delete(communityForDeletion);
    }
}
