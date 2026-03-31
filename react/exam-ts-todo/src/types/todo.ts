/** 할 일 한 건 */
export interface Todo {
  id: number;
  text: string;
  done: boolean;
}

/** 목록 필터 (타입 유니온으로 허용 값만 제한) */
export type TodoFilter = "all" | "active" | "completed";