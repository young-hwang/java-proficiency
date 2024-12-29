package me.webservice;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

public class FileMemberRepository implements MemberRepository {
    private final String DELIMITER = ",";
    private final String FILE_PATH = "temp/members-text.data";

    @Override
    public void add(Member member) {
       try(BufferedWriter writer = Files.newBufferedWriter(Paths.get(FILE_PATH), UTF_8, StandardOpenOption.APPEND)) {
           writer.write(member.getId() + DELIMITER + member.getName() + DELIMITER + member.getAge());
           writer.newLine();
       } catch (IOException e) {
           throw new RuntimeException(e);
       }
    }

    @Override
    public List<Member> findAll() {
        try(BufferedReader reader = Files.newBufferedReader(Paths.get(FILE_PATH), UTF_8)) {
            String line;
            List<Member> members = new ArrayList<>();
            while((line = reader.readLine()) != null) {
                String[] split = line.split(DELIMITER);
                Member member = new Member(split[0], split[1], Integer.parseInt(split[2]));
                members.add(member);
            }
            return members;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
