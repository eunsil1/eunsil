import dotenv from 'dotenv';
import { dirname, join } from 'path';
import { fileURLToPath } from 'url';
import mysql from 'mysql2/promise';

const __dirname = dirname(fileURLToPath(import.meta.url));
dotenv.config({ path: join(__dirname, '.env') });

const MYSQL_HOST = process.env.MYSQL_HOST ?? 'localhost';
const MYSQL_PORT = process.env.MYSQL_PORT ?? '3306';
const MYSQL_USER = process.env.MYSQL_USER ?? 'root';
/** .env에 키가 없을 때만 기본값(로컬 예시). 빈 값 MYSQL_PASSWORD= 은 "비밀번호 없음"으로 둠 */
const MYSQL_PASSWORD =
  process.env.MYSQL_PASSWORD === undefined ? '1234' : process.env.MYSQL_PASSWORD;
const MYSQL_DATABASE = process.env.MYSQL_DATABASE ?? 'kculture_platform';

export const pool = mysql.createPool({
  host: MYSQL_HOST,
  port: Number(MYSQL_PORT, 10),
  user: MYSQL_USER,
  password: MYSQL_PASSWORD,
  database: MYSQL_DATABASE,
  waitForConnections: true,
  connectionLimit: 10,
});
