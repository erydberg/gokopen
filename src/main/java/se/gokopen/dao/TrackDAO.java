package se.gokopen.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import se.gokopen.model.Track;

@Repository
public class TrackDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void save(Track track) {
        sessionFactory.getCurrentSession().saveOrUpdate(track);
    }

    public void delete(Track track) {
        sessionFactory.getCurrentSession().delete(track);
    }

    @SuppressWarnings("unchecked")
    public Track getById(Integer id) throws TrackNotFoundException {
        Track track = null;

        List<Track> tracks = (List<Track>) sessionFactory.getCurrentSession()
                .createQuery("from Track track where track.trackId= :trackid").setParameter("trackid", id).list();
        if (tracks == null || tracks.isEmpty() || tracks.size() > 1) {
            throw new TrackNotFoundException("Hittar inte spåret med id: " + id);
        }
        track = tracks.get(0);
        return track;
    }

    public void deleteId(Integer id) {
        try {
            Track track = getById(id);
            delete(track);
        } catch (TrackNotFoundException e) {
            System.out.println("Spåret fanns inte att ta bort");
        }
    }

    @SuppressWarnings("unchecked")
    public List<Track> getAllTracks() {
        return sessionFactory.getCurrentSession().createQuery("from Track").list();
    }
}
