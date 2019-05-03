select c1, c2, c3, c4, c5, c6
from (
	select c1, c2, c3, c4, c5, c6
	from 여행활동 join 현지투어 on x.no=a.no
	
	union
	
	select c1, c2, c3, c4, c5, c6
	from 여행활동 join 레스토랑 on x.no=a.no
	
	union
	
	select c1, c2, c3, c4, c5, c6
	from 여행활동 join 레스토랑 on x.no=a.no
)
order by 활동순서 asc