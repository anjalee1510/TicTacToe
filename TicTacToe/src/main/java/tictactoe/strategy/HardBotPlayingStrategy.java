package tictactoe.strategy;

import tictactoe.models.*;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

public class HardBotPlayingStrategy implements BotPlayingStrategy {

    private static final String API_KEY = "YOUR_OPENAI_API_KEY"; // use env variable in prod
    private static final String MODEL = "gpt-3.5-turbo";

    @Override
    public Cell suggestMove(Player player, Board board) {
        String boardJson = convertBoardToJson(board);
        String symbol = String.valueOf(player.getSymbol());
        String prompt = "Given this Tic Tac Toe board and the bot's symbol '" + symbol + "', suggest the best move as JSON {\"row\": x, \"col\": y}:\n" + boardJson;

        String response = callOpenAiApi(prompt);
        Map<String, Integer> move = parseMoveFromResponse(response);
        return board.getCells().get(move.get("row")).get(move.get("col"));
    }

    private String callOpenAiApi(String message) {
        String url = "https://api.openai.com/v1/chat/completions";

        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Authorization", "Bearer " + API_KEY);
            con.setRequestProperty("Content-Type", "application/json");

            String body = "{\"model\": \"" + MODEL + "\", \"messages\": [{\"role\": \"user\", \"content\": \"" + message + "\"}]}";
            con.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream());
            writer.write(body);
            writer.flush();
            writer.close();

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return extractContentFromResponse(response.toString());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String extractContentFromResponse(String response) {
        int start = response.indexOf("content") + 11;
        int end = response.indexOf("\"", start);
        return response.substring(start, end);
    }

    private String convertBoardToJson(Board board) {
        StringBuilder sb = new StringBuilder("[\n");
        for (List<Cell> row : board.getCells()) {
            sb.append("  [");
            for (Cell cell : row) {
                if (cell.getCellState() == CellState.FREE) {
                    sb.append("\" \", ");
                } else {
                    sb.append("\"").append(cell.getPlayer().getSymbol()).append("\", ");
                }
            }
            sb.setLength(sb.length() - 2);
            sb.append("],\n");
        }
        sb.setLength(sb.length() - 2);
        sb.append("\n]");
        return sb.toString();
    }

    private Map<String, Integer> parseMoveFromResponse(String content) {
        Map<String, Integer> move = new HashMap<>();
        content = content.replaceAll("[^0-9:,]", "").trim();
        String[] parts = content.split(",");
        move.put("row", Integer.parseInt(parts[0].split(":")[1].trim()));
        move.put("col", Integer.parseInt(parts[1].split(":")[1].trim()));
        return move;
    }
}
