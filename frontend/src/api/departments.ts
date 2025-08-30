import { Department, API_BASE_URL } from "./types";

export const getDepartments = async (): Promise<Department[]> => {
  try {
    const response = await fetch(`${API_BASE_URL}/api/departments`);
    if (!response.ok) {
      // レスポンスのステータスが200番台以外の場合はエラーを投げる
      throw new Error(`部署の取得に失敗しました: ${response.status}`);
    }
    const departments: Department[] = await response.json();
    return departments;
  } catch (error) {
    console.error("部署の取得エラー:", error); // わかりやすくするためにコンソールに出力
    throw error;
  }
};
