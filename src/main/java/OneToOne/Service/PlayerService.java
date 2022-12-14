package OneToOne.Service;

import OneToOne.Entity.Player;
import OneToOne.Entity.PlayerProfile;
import OneToOne.Repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository repo;

    public List<Player> getAllPlayer(){
        return repo.findAll();
    }

    public Player getPlayerByID(@PathVariable("id") int id){
        Optional<Player> player = repo.findById(id);
        if(player.isPresent())  return player.get();
        throw new RuntimeException("Payer with Id="+id+" not found.");
    }

    public Player addPlayer(Player player){
        return repo.save(player);
    }

    public void deletePlayerById(int id){
        Optional<Player> p = repo.findById(id);
        if(p.isEmpty())  throw new RuntimeException("Payer with Id="+id+" not found.");
        repo.delete(p.get());
    }

    public Player assignProfile(int id, PlayerProfile profile){
        // We just take id{player} and profile_id and find player by its id and assign profile.
        Player player = repo.findById(id).get();

        player.setPlayerProfile(profile);

        return repo.save(player);

    }
}
