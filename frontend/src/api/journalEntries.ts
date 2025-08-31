import { JournalEntry, API_BASE_URL } from "./types";

export const getJournalEntries = async (): Promise<JournalEntry[]> => {
  try {
    const response = await fetch(`${API_BASE_URL}/api/journal-entries`);
    if (!response.ok) {
      // レスポンスのステータスが200番台以外の場合はエラーを投げる
      throw new Error(`仕訳表の取得に失敗しました: ${response.status}`);
    }
    const journalEntry: JournalEntry[] = await response.json();
    return journalEntry;
  } catch (error) {
    console.error("仕訳表の取得エラー:", error); // わかりやすくするためにコンソールに出力
    throw error;
  }
};
