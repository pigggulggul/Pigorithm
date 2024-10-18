-- 코드를 입력하세요
# 2022년 10월에 작성된 게시글 제목, 게시글 ID, 댓글 ID, 댓글 작성자 ID, 댓글 내용, 댓글 작성일
SELECT board.title, board.board_ID, reply.reply_id, reply.writer_id, reply.contents, date_format(reply.created_date,'%Y-%m-%d') as created_date
from used_goods_board as board inner join used_goods_reply as reply
on board.board_id = reply.board_id
where year(board.created_date) = '2022' and month(board.created_date)='10'
order by reply.created_date, board.title