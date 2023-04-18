package id_authentication.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "member_number", length=50)
    @NonNull
    private String memberNumber;
    @Column(name = "first_name", length=100)
    @NonNull
    private String firstName;
    @Column(name = "last_name", length=100)
    private String lastName;
    @Column(name = "user_name", length=100)
    @NonNull
    private String userName;
    @Column(name = "password")
    @NonNull
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "member_id")
    private List<Membership> memberships;

    @OneToMany(mappedBy = "member",fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Badge> badges=new ArrayList<Badge>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "member_id")
    private List<CheckInRecord> checkInRecords;

    public Member(@NonNull String memberNumber, @NonNull String firstName, String lastName, @NonNull String userName, @NonNull String password, List<Badge> badges) {
        this.memberNumber = memberNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.badges = badges;
    }

    public void addMembership(Membership membership) {
        if (memberships == null) {
            memberships = new ArrayList<>();
        }
        memberships.add(membership);
    }

    public void removeMembership(Membership membership) {
        if (memberships != null) {
            memberships.remove(membership);
        }
    }

    public void addCheckInRecord(CheckInRecord checkInRecord) {
        if (checkInRecords == null) {
            checkInRecords = new ArrayList<>();
        }
        checkInRecords.add(checkInRecord);
    }

    public void removeCheckInRecord(CheckInRecord checkInRecord) {
        if (checkInRecords != null) {
            checkInRecords.remove(checkInRecord);
        }
    }

    public void addPlans(List<Plan> planList) {
    }
}
